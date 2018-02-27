/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.habeis.api.resources.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

/**
 *
 * @author diego
 */
public class DateUtils {

    public static Date parseDate(String date) {

        try {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

      public static Date getDate() throws UnknownHostException, IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String ntpServer = "a.st1.ntp.br"; //servidor de horario brasileiro
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(ntpServer);
        TimeInfo timeInfo = timeClient.getTime(inetAddress);
        long returnTime = timeInfo.getReturnTime();
        Date time = new Date(returnTime);
        time.setHours( time.getHours() - 3);

        return time;
    }
    public static String dateNow() throws UnknownHostException, IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return dateFormat.format(getDate());
    }

    public static String timeNow() throws IOException {

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        return timeFormat.format(getDate());
    }
}
