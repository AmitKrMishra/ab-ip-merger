package ab.bank.ip.core;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ab.bank.ip.model.AbFile;

/**
 *This class process the text file. Parse the content of file.
 *
 * @author Reverside
 */
public class ProcessTextFile implements IFileReader {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    //Input file record pattern 
    Pattern fileRecordPattern = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]):( \\d+(,\\d+)*)");
    
    // function to removes the duplicate and sort the number corresponding.
     Function<String, String> sortNumListFunc = csvStr -> {
        logger.debug("Inside sortNumListFunc");
        String[] numArray = csvStr.split(",");
        List<Integer> intList = new ArrayList<>();
        for (String strNum : numArray) {
            intList.add(Integer.valueOf(strNum.trim()));
        }
        logger.debug("NumList before sort: {}", intList);
        // Get collection without duplicate i.e. distinct only
        List<Integer> distinctNumList = intList.stream().distinct().collect(Collectors.toList());
        logger.debug("DistinctNumList: {}", distinctNumList);
        Collections.sort(distinctNumList);
        StringBuilder csvSb = new StringBuilder();
        for (int i : distinctNumList) {
            csvSb.append(i);
            csvSb.append(",");
        }
        logger.debug("NumList after Sort: {}", csvSb);
        return csvSb.toString();
    };

    //function to validate file record formate.
    Function<String, String> validateFileRecordFunc = fileRecord -> {
		String isInvalidFormat = "true";
		Matcher matcher = fileRecordPattern.matcher(fileRecord);
		if (!matcher.matches()) {
			System.out.println("Invalid record found:" + fileRecord);
			isInvalidFormat = "false";
		}
		return isInvalidFormat;
	};
	
    // function to filters the ip-address and numbers into hashmap.
    Function<Stream<String>, HashMap<String, String>> ipNumListFilterFunc = ipStream -> {
        logger.debug("Inside ipNumListFilterFunc");
        List<String> ipStreamStrList = ipStream.sorted().collect(Collectors.toList());
        HashMap<String, String> iphm = new HashMap<>();
        for (String ipString : ipStreamStrList) {
        	if(validateFileRecordFunc.apply(ipString)=="true") {
        		String[] p = ipString.split(":");
                if (p[0] != null && p[0].length() > 0 && p[1] != null && p[1].length() > 0) {
                    logger.debug("Calling sortNumListFunc for ip: {}", p[0]);
                    iphm.put(p[0], sortNumListFunc.apply(p[1]));
                }	
        	}else {
        		System.out.println("Error in input file content.");
        		System.exit(0);
        	}
        }
        logger.debug("ip hasMap sorted NumList: {}", iphm);
        return iphm;
    };

    /**
     * {@inheritDoc}
     *
     * This method filters the file content.
     */
    public HashMap<String, String> processFiles(AbFile abFile) throws IOException {
        logger.debug("Inside processFiles {}",abFile.getFileName());
        System.out.println("\nFilename: "+abFile.getFileName());
        HashMap<String, String> fileMap;
        // read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(abFile.getFileName()))) {
            fileMap = ipNumListFilterFunc.apply(stream);
        } catch (IOException e) {
            logger.debug("IOException: {}", e.getMessage());
            throw new IOException();
        }
        if(fileMap!=null) {
        	System.out.println("File \""+abFile.getFileName() +" \" having valid content. Ready for merge.");
        }else {
        	System.out.println("Invalid content found in file:"+abFile.getFileName());
        }
        return fileMap;
    }

    /**
     * This method merge the ip-address and sort the number.
     *
     * @param fileHmap a {@link java.util.HashMap} object.
     */
    public boolean mergeFileMap(HashMap<String, HashMap<String, String>> fileHmap) {
        logger.debug("Inside mergeFileMap");
        boolean MERGE_STATUS = false;
        // The following code filter common ip-address found in files.
        Iterator keySetIterator = fileHmap.keySet().iterator();
        while (keySetIterator.hasNext()) {
            HashMap<String, String> fileMap1 = fileHmap.get(keySetIterator.next());
            HashMap<String, String> fileMap2 = fileHmap.get(keySetIterator.next());
            HashMap<String, String> commonIPMap = new HashMap<>();
            fileMap1.forEach((key, value) -> {
                StringBuilder sb = new StringBuilder();
                if (fileMap2.containsKey(key)) {
                    sb.append(value);
                    sb.append((String) fileMap2.get(key));
                    commonIPMap.put(key, sortNumListFunc.apply(sb.toString()));
                } else {
                    fileMap2.put(key, fileMap1.get(key));
                }
            });
            fileMap2.putAll(commonIPMap);
            logger.debug("Common ip-address in files: {}", fileMap2);

            // The following code converts the IP address to long value first
            // and then do the sorting.
            Comparator<String> ipComparator = new Comparator<String>() {
                @Override
                public int compare(String ip1, String ip2) {
                    return toNumeric(ip1).compareTo(toNumeric(ip2));
                }
            };
            SortedSet<String> ips = new TreeSet<String>(ipComparator);
            ips.addAll(fileMap2.keySet());
            logger.debug("Merged File IP and number is sorted order: {}", ips);
            ips.stream().forEach(ip -> {
                System.out.println(ip + ":" + fileMap2.get(ip));
            });
            MERGE_STATUS=true;
        }
        return MERGE_STATUS;
    }

    /**
     * The Method converts the ip-address to long.
     * 
     * @param ip,
     *            id-address.
     * @return long value.
     */
    private Long toNumeric(String ip) {
        logger.debug("Inside toNumeric");
        Scanner sc = new Scanner(ip).useDelimiter("\\.");
        Long l = (sc.nextLong() << 24) + (sc.nextLong() << 16) + (sc.nextLong() << 8) + (sc.nextLong());
        sc.close();
        return l;
    }
}
