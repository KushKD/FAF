package com.hp.dit.Flight.Application.Form.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author      Mobile Seva < msdp@cdac.in >
 * <p>Kindly add require Jar files to run Java client</p><p>
 * Apache commons-codec-1.9
 * <br>Apache commons-httpclient-3.1
 * <br>Apache commons-logging-1.2
 * @see <a href="https://mgov.gov.in/doc/RequiredJars.zip">Download required Jar files</a>
 */
public class SMSServices {

    private static final Logger logger = LoggerFactory.getLogger(SMSServices.class);

    /**
     * Send Single text SMS
     * @param username : Department Login User Name
     * @param password : Department Login Password
     * @param message  : Message e.g. 'Welcome to mobile Seva'
     * @param senderId	: Department allocated SenderID
     * @param mobileNumber : Single Mobile Number e.g. '99XXXXXXX'
     * @param secureKey :  Department key generated by login to services portal
     * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID = 150620161466003974245msdgsms'
     * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code details</a>
     *
     */

    public String sendSingleSMS(String username, String password , String message , String senderId, String mobileNumber,String secureKey, String templateid){


        String responseString = "";
        SSLSocketFactory sf=null;
        SSLContext context=null;
        String encryptedPassword;
        try {
            //context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version 6
            context=SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
            context.init(null, null, null);
            sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            Scheme scheme=new Scheme("https",443,sf);
            HttpClient client=new DefaultHttpClient();
            client.getConnectionManager().getSchemeRegistry().register(scheme);
            HttpPost post=new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequest");
            encryptedPassword  = MD5(password);
            String genratedhashKey = hashGenerator(username, senderId, message, secureKey);
            List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
            nameValuePairs.add(new BasicNameValuePair("senderid", senderId));
            nameValuePairs.add(new BasicNameValuePair("content", message));
            nameValuePairs.add(new BasicNameValuePair("smsservicetype", "singlemsg"));
            nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
            nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
            nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response=client.execute(post);
            BufferedReader bf=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line="";
            while((line=bf.readLine())!=null){
                responseString = responseString+line;

            }
            System.out.println(responseString);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseString;
    }




    /**
     * Send Single OTP text SMS
     *</p><p> Use only in case of OTP related message</p><p>
     * Messages other than OTP will not be delivered to the users
     * @param username : Department Login User Name
     * @param password : Department Login Password
     * @param message  : Message e.g. 'Welcome to mobile Seva'
     * @param senderId	: Department allocated SenderID
     * @param mobileNumber : Single Mobile Number e.g. '99XXXXXXX'
     * @param secureKey :  Department key generated by login to services portal
     * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID = 150620161466003974245msdgsms'
     * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code details</a>
     *
     */

    public String sendOtpSMS(String username, String password , String message , String senderId, String mobileNumber,String secureKey,String templateid){

        String responseString = "";
        SSLSocketFactory sf=null;
        SSLContext context=null;
        String encryptedPassword;
        X509TrustManager tm = null;
        try {
            //context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version 6
            context=SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
            tm = new X509TrustManager() {

                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            context.init(null, new TrustManager[]{tm}, null);
            //context.init(null, null, null);
            //	sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            sf=new SSLSocketFactory(context, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);


            Scheme scheme=new Scheme("https",443,sf);
            HttpClient client=new DefaultHttpClient();
            client.getConnectionManager().getSchemeRegistry().register(scheme);
            HttpPost post=new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequest");
            encryptedPassword  = MD5(password);
            String genratedhashKey = hashGenerator(username, senderId, message, secureKey);
            List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
            nameValuePairs.add(new BasicNameValuePair("senderid", senderId));
            nameValuePairs.add(new BasicNameValuePair("content", message));
            nameValuePairs.add(new BasicNameValuePair("smsservicetype", "otpmsg"));
            nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
            nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
            nameValuePairs.add(new BasicNameValuePair("templateid", templateid));

            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response=client.execute(post);
            BufferedReader bf=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line="";
            while((line=bf.readLine())!=null){
                responseString = responseString+line;

            }
            System.out.println(responseString);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseString;

    }



    protected String hashGenerator(String userName, String senderId, String content, String secureKey) {
        // TODO Auto-generated method stub
        StringBuffer finalString=new StringBuffer();
        finalString.append(userName.trim()).append(senderId.trim()).append(content.trim()).append(secureKey.trim());
        //		logger.info("Parameters for SHA-512 : "+finalString);
        String hashGen=finalString.toString();
        StringBuffer sb = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(hashGen.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }






    /****
     * Method  to convert Normal Plain Text Password to MD5 encrypted password
     ***/

    private static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] md5 = new byte[64];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5 = md.digest();
        return convertedToHex(md5);
    }

    private static String convertedToHex(byte[] data)
    {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < data.length; i++)
        {
            int halfOfByte = (data[i] >>> 4) & 0x0F;
            int twoHalfBytes = 0;

            do
            {
                if ((0 <= halfOfByte) && (halfOfByte <= 9))
                {
                    buf.append( (char) ('0' + halfOfByte) );
                }

                else
                {
                    buf.append( (char) ('a' + (halfOfByte - 10)) );
                }

                halfOfByte = data[i] & 0x0F;

            } while(twoHalfBytes++ < 1);
        }
        return buf.toString();
    }

}