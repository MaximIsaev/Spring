package com.get_news_feed_file;


import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadNewsFeedFile {

    private String newsFeedsUrl = "http://info.epam.com/info/rss.builder.feedrss.builder.feed?language=en&news=true&channels=official&channels=production&channels=career&channels=apps&channels=articles&channels=nowork&globalEvents=true&localEvents=true&my_unit=true&communities=AEI&communities=astana&communities=bicc&communities=big-data&communities=brest&communities=canada&communities=cdp-apac&communities=cdp-belarus&communities=cdphu&communities=cdpkzam&communities=cdpna&communities=cdp-russia&communities=cdp-ukraine&communities=cto&communities=cmcc&communities=russia&communities=contentcc&communities=cmsp&communities=dance-club-minsk&communities=dep&communities=dnipropetrovsk&communities=DrivingClubMinsk&communities=ecsp&communities=crm&communities=poland&communities=usa&communities=EACC&communities=epam-ic-center&communities=expedia&communities=global-talent-acquisition&communities=gomel&communities=grodno&communities=HRCommunityBY&communities=itservices&communities=im&communities=izhevsk&communities=javacc&communities=kharkiv&communities=kyiv&communities=llpd&communities=lviv&communities=MSFTCC&communities=manager-s-adviser&communities=ManagersCommunity&communities=Miniqs&communities=minsk&communities=mobilecc&communities=mogilev&communities=moscow&communities=PHP-ST&communities=qa-community&communities=rdua&communities=RMCommunity&communities=ryazan&communities=sapcc&communities=sap-solution-practice&communities=std&communities=samara&communities=saratov&communities=sposad&communities=sportportal-minsk&communities=st-petersburg&communities=strategic-alliances-and-partners0&communities=szeged&communities=tr-cbu-community&communities=tm&communities=TheNewsClub&communities=travelsolutions&communities=tver&communities=vinnitsa&communities=vitebsk&communities=zurich&albums=true&benefits=true&benefitCountry=russia&benefitCity=tver&benefitCategory=Health&benefitCategory=Family_Care&benefitCategory=Sport_Fitness&benefitCategory=Office_and_Home&benefitCategory=Education&benefitCategory=Food&benefitCategory=Auto&benefitCategory=Beauty&benefitCategory=Travel&benefitCategory=Entertainment&benefitCategory=Other&benefitCategory=corporate_programs";
    private String outputFeedFolderPath = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\NewsStorage";
    private String outputFeedFilePath = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\NewsStorage\\NewsFeed.txt";

    private File outputFeedFolder = new File(outputFeedFolderPath);
    private File outputFeedFile = new File(outputFeedFilePath);

    public void download(String strURL) {
        try {
            URL connection = new URL(strURL);
            ReadableByteChannel rbc = Channels.newChannel(connection.openStream());
            if (validateOutputFeedFile()) {
                write(rbc, getOutputFeedFilePath());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String getNewsFeedsUrl() {
        return newsFeedsUrl;
    }

    public String getOutputFeedFilePath() {
        return outputFeedFilePath;
    }

    public File getOutputFeedFile() {
        return outputFeedFile;
    }

    public boolean validateOutputFeedFile() {

        boolean flag = false;

        if (createNewsFolder()) {
            flag = createOutputFeedFile();

        } else {
            flag = createOutputFeedFile();
        }
        return flag;
    }

    public void write(ReadableByteChannel in, String strPath) {

        try {
            FileOutputStream fos = new FileOutputStream(strPath);
            fos.getChannel().transferFrom(in, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createNewsFolder() {

        try {
            if (!outputFeedFolder.exists()) {
                outputFeedFolder.mkdirs();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createOutputFeedFile() {

        try {
            if (!outputFeedFile.exists()) {
                outputFeedFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
