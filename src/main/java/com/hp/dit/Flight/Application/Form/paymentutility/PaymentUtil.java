package com.hp.dit.Flight.Application.Form.paymentutility;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class PaymentUtil {

    //private static final String paymentKey = "7rnFly"; //test
    private static final String paymentKey = "WmUizd"; //live  dOqRDsrz

   // private static final String paymentSalt = "pjVQAWpA";  //test
    private static final String paymentSalt = "dOqRDsrz";  //live  WmUizd
    private static final String serverPath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/paymentResponse").toUriString();

    //private static final String sUrl = "http://localhost:8080/paymentResponse";
    private static final String fUrl = serverPath;
    private static final String sUrl = serverPath;


    //private static final String fUrl = "http://localhost:8080/paymentResponse";

    public static PaymentDetail populatePaymentDetail(PaymentDetail paymentDetail) {
        String hashString = "";
        Random rand = new Random();
        String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
        String txnId = hashCal("SHA-256", rndm).substring(0, 12);
        paymentDetail.setTxnId(txnId);
        String hash = "";
        //String otherPostParamSeq = "phone|surl|furl|lastname|curl|address1|address2|city|state|country|zipcode|pg";
        String hashSequence = "key|txnid|amount|productinfo|firstname|email|||||||||||";
        hashString = hashSequence.concat(paymentSalt);
        hashString = hashString.replace("key", paymentKey);
        hashString = hashString.replace("txnid", txnId);
        hashString = hashString.replace("amount", Double.toString(paymentDetail.getAmount()));
        hashString = hashString.replace("productinfo", paymentDetail.getProductInfo());
        hashString = hashString.replace("firstname", paymentDetail.getName());
        hashString = hashString.replace("email", paymentDetail.getEmail());

        hash = hashCal("SHA-512", hashString);
        paymentDetail.setHash(hash);
        paymentDetail.setfUrl(fUrl);
        paymentDetail.setsUrl(sUrl);
        paymentDetail.setKey(paymentKey);
        System.out.println(paymentDetail.toString());
        return paymentDetail;
    }

    public static String hashCal(String type, String str) {
        byte[] hashseq = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException nsae) {
        }
        return hexString.toString();
    }

    public static String verifyHash(PaymentCallback paymentCallback) {
        String hashString = "";
        String hashSequence = null;
        String hash;
        //sha512(SALT|status||||||udf5|udf4|udf3|udf2|udf1|email|firstname|productinfo|amount|txni d|key)



        if(paymentCallback.getAdditionalCharges().isEmpty()){
            //sha512(SALT|status||||||udf5|udf4|udf3|udf2|udf1|email|firstname|productinfo|amount|txni d|key)
            hashSequence = paymentSalt + "|" + paymentCallback.getStatus() + "|||||||||||" + paymentCallback.getEmail() + "|" + paymentCallback.getFirstname() + "|" + paymentCallback.getProductinfo() + "|" + paymentCallback.getAmount() + "|" + paymentCallback.getTxnid() + "|";

        }else{
            //sha512(SALT|status||||||udf5|udf4|udf3|udf2|udf1|email|firstname|productinfo|amount|txni d|key)
            hashSequence = paymentCallback.getAdditionalCharges()+"|"+paymentSalt + "|" + paymentCallback.getStatus() + "|||||||||||" + paymentCallback.getEmail() + "|" + paymentCallback.getFirstname() + "|" + paymentCallback.getProductinfo() + "|" + paymentCallback.getAmount() + "|" + paymentCallback.getTxnid() + "|";

        }

         hashString = hashSequence.concat(paymentCallback.getKey());
        System.out.println(hashString);
        hash = hashCal("SHA-512", hashString);
        System.out.println(hash);

        return hash;


    }


    //This function is used to double check payment
    public static String verifyPayment(String txnid) {
        String command = "verify_payment";
        String hashstr = paymentKey + "|" + command + "|" + txnid + "|" + paymentSalt;

        String hash = hashCal("SHA-512", hashstr);

        StringBuilder response = new StringBuilder();

        try {
            //for production
            //String wsUrl = "https://info.payu.in/merchant/postservice.php?form=1";

            //for test
            URL wsUrl = new URL("https://test.payu.in/merchant/postservice.php?form=2");

            Map<String, Object> params = new LinkedHashMap<>();
            params.put("key", paymentKey);
            params.put("hash", hash);
            params.put("var1", txnid);
            params.put("command", command);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));


            for (int c; (c = in.read()) >= 0; ) {
                response.append((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String resp = response.toString();
        System.out.println(resp.toString());

        if (resp.indexOf("\"status\":\"success\"") > 1)
            return resp;
        else
            return resp;

    }

    public static boolean verifyPaymentcheck(String txnid) {
        String command = "verify_payment";
        String hashstr = paymentKey + "|" + command + "|" + txnid + "|" + paymentSalt;

        String hash = hashCal("SHA-512", hashstr);

        StringBuilder response = new StringBuilder();

        try {
            //for production
            //String wsUrl = "https://info.payu.in/merchant/postservice.php?form=1";

            //for test
            URL wsUrl = new URL("https://test.payu.in/merchant/postservice.php?form=2");

            Map<String, Object> params = new LinkedHashMap<>();
            params.put("key", paymentKey);
            params.put("hash", hash);
            params.put("var1", txnid);
            params.put("command", command);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));


            for (int c; (c = in.read()) >= 0; ) {
                response.append((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String resp = response.toString();
        System.out.println(resp.toString());

        if (resp.indexOf("\"status\":\"success\"") > 1)
            return true;
        else
            return false;

    }

}