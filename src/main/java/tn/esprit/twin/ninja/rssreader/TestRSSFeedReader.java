/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.twin.ninja.rssreader;

/**
 * It tests RSSFeedReader class.
 */
public class TestRSSFeedReader {
    public static void main(String[] args){
        System.out.println(new RSSFeedReader().getItems("http://www.feedforall.com/blog-feed.xml"));
        
    }
             
}
