package com.example.sunilm.nycnewsapp;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by sunilm on 1/23/2018.
 */

public class XmlParserClass  {

   static public class Util {



       static ArrayList<NewsClass> parseNews(InputStream in) throws XmlPullParserException, IOException {
           XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
           parser.setInput(in,"UTF-8");
           ArrayList<NewsClass> newsList = new ArrayList<>();
           NewsClass newsClass = null;
           int event = parser.getEventType();
           while (event!= XmlPullParser.END_DOCUMENT) {

               switch (event) {
                   case XmlPullParser.START_TAG:
                       if(parser.getName().trim().equals("item")) {
                           newsClass = new NewsClass();
                           //newsClass.setTitle(parser.getText());
                       }
                      else if(parser.getName().equals("title")) {
                           newsClass = new NewsClass();
                           newsClass.setTitle(parser.nextText());
                       }
                       else if(parser.getName().trim().equals("description")) {

                           newsClass.setDescription(parser.nextText());
                       }
                       else if(parser.getName().equals("media:content")) {

                           newsClass.setContent(parser.getAttributeValue("","url"));
                       }
                       else if(parser.getName().equals("link")) {

                           newsClass.setLink(parser.nextText());
                       }
                       else if(parser.getName().equals("pubDate")) {

                           newsClass.setPubDate(parser.nextText());
                       }  break;
                   case XmlPullParser.END_TAG:
                       if(parser.getName().equals("item")) {
                           newsList.add(newsClass);
                           newsClass = null;
                       }
                       break;
                    default:
                       break;

               }

             event = parser.next();

           }

           return newsList;
           }
       }
   }

