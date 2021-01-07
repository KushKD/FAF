package com.hp.dit.Flight.Application.Form.paymentutility;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PaymentUtil {

    private static final String paymentKey = "7rnFly";

    private static final String paymentSalt = "pjVQAWpA";
    private static final String serverPath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/paymentResponse").toUriString();

    //private static final String sUrl = "http://localhost:8080/paymentResponse";
        private static final  String  fUrl = serverPath;
    private static final String sUrl = serverPath;


    //private static final String fUrl = "http://localhost:8080/paymentResponse";

    public static PaymentDetail populatePaymentDetail(PaymentDetail paymentDetail){
        String hashString = "";
        Random rand = new Random();
        String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
        String txnId =  hashCal("SHA-256", rndm).substring(0, 12);
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

    public static boolean verifyPayment(PaymentCallback paymentCallback){
        String hashString="";
        String hash;
            String hashSequence = paymentSalt+"|"+paymentCallback.getStatus()+"|||||||||||"+paymentCallback.getEmail()+"|"+paymentCallback.getFirstname()+"|"+paymentCallback.getProductinfo()+"|"+paymentCallback.getAmount()+"|"+paymentCallback.getTxnid()+"|";
            hashString=hashSequence.concat(paymentCallback.getKey());
            System.out.println(hashString);
            hash=hashCal("SHA-512",hashString);
            //out.println(hash);
            if(paymentCallback.getHash().equals(hash)){
                System.out.println("Payment successfull (No Additional Charges) - "+"<br />");
                System.out.println("Amount:"+paymentCallback.getAmount()+"<br />");
                System.out.println("Status of Transaction:"+paymentCallback.getStatus()+"<br />");
                return true;
                //Do success order processing here...
                //Additional step - Use verify payment api to double check payment.
//                if(verifyPayment(key,salt,txnid)){
//                    out.println("<h2>Payment Verified...</h2><br />");
//                }
//                else {
//                    out.println("<h1>Payment Not Verified...</h1><br />");
//                }

            }
            else{
                System.out.println("Hash Mismatch");
                return false;
            }




    }

}