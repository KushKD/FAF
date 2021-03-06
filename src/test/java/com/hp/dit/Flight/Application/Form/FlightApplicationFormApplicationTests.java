package com.hp.dit.Flight.Application.Form;

import com.hp.dit.Flight.Application.Form.entities.RolesEntity;
import com.hp.dit.Flight.Application.Form.entities.UserEntity;
import com.hp.dit.Flight.Application.Form.repositories.RolesRepository;
import com.hp.dit.Flight.Application.Form.repositories.UserRepository;
import com.hp.dit.Flight.Application.Form.utilities.Constants;
import com.hp.dit.Flight.Application.Form.utilities.SMSServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class FlightApplicationFormApplicationTests {

//	@Autowired
//	RolesRepository rolesRepository;
//
//
//    @Autowired
//	UserRepository userRepository;
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void createUser(){
//
//        UserEntity user1 = new UserEntity();
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        user1.setUsername("kush");
//        user1.setEmail("kushkumardhawan@gmail.com");
//        user1.setGender("M");
//        user1.setIs_deleted(false);
//        user1.setPassword(encoder.encode("Demo@123"));
//        user1.setMobileNumber(9459619235L);
//        user1.setActive(true);
//
//        Optional<RolesEntity> role = rolesRepository.findById(2L);
//		List<RolesEntity> list = new ArrayList<>();
//		list.add(role.get());
//        user1.setRoles(list);
//
//
//	 userRepository.save(user1);
//	}
//
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void createRoles() {
//
//        RolesEntity roles = new RolesEntity();
//        roles.setRoleName("Admin");
//        roles.setRoleDescription("Super Admin of the Website");
//        roles.setActive(true);
//
//
//
//        rolesRepository.save(roles);
//
//    }
////
//    @Test
//    void bCryptPassword(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        encoder.encode("fagu@123");
//        System.out.printf("\n");
//        System.out.printf(encoder.encode("fagu@123"));
//        System.out.printf("\n");
//    }
//

//  @Test
//  void checkRandom(){
//
//      System.out.println("!@!@!@!@!"+random24.randomDecimalString(6));
//  }


//    @Test
//    void testDate() throws ParseException {
//        DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = originalFormat.parse("14-07-2020");
//        String formattedDate = targetFormat.format(date);
//        System.out.println(formattedDate);
//    }

//    @Test
//    private void date(){
//        System.out.println("-----Current time of a different time zone using LocalTime-----");
//        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
//        LocalTime localTime=LocalTime.now(zoneId);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String formattedTime=localTime.format(formatter);
//        System.out.println("Current time of the day in Los Angeles: " + formattedTime);
//    }

//    @Test
//    public  void convertStringSqlDate() {
//
//
//
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        java.util.Date parsed = null;
//
//        try {
//            parsed = format.parse("14-07-2020");
//        } catch (ParseException e) {
//
//        }
//
//        java.sql.Date sql = new java.sql.Date(parsed.getTime());
//        System.out.println(sql);
//       // return sql;
//    }


	//  @Test
//    public  void checkCrypto() throws ParserConfigurationException, IOException, SAXException {
//
//            CryptographyAES AES = new CryptographyAES();
//
//           String decrypt = AES.decryptFile(Constants.stringSample,Constants.securityKeyAES);
//            System.out.println( " Decryption " +decrypt);
//
//
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse( new InputSource( new StringReader( decrypt ) ) );
//
//            doc.getDocumentElement().normalize();
//
//            NodeList nodeList = doc.getElementsByTagName("VehicleDetails");
//            List<VehicleDetailsObject> vehicleDetails = new ArrayList<>();
//
//            //loop all available student nodes
//            for (int i = 0; i < nodeList.getLength(); i++) {
//
//                Node node = nodeList.item(i);
//
//                if(node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element elem = (Element) node;
//                    VehicleDetailsObject data = new VehicleDetailsObject();
//                    data.setRcChassisNo(elem.getElementsByTagName("rc_chasi_no").item(0).getTextContent());
//                    data.setRcEngineNumber(elem.getElementsByTagName("rc_eng_no").item(0).getTextContent());
//                    data.setRcFitUpto(elem.getElementsByTagName("rc_fit_upto").item(0).getTextContent());
//                    data.setRcRegisteredAt(elem.getElementsByTagName("rc_registered_at").item(0).getTextContent());
//                    data.setRcStatus(elem.getElementsByTagName("rc_status").item(0).getTextContent());
//                    data.setRcRegistrationNo(elem.getElementsByTagName("rc_regn_no").item(0).getTextContent());
//                    data.setRcStatusAsOn(elem.getElementsByTagName("rc_status_as_on").item(0).getTextContent());
//                    System.out.println(data.toString());
//
//                    vehicleDetails.add(data);
//                }
//            }
//
//
//
//
//    }

	//postData

//    @Test
//    public void getData() {
//
//            HTTP http = new HTTP();
//            String data = http.postData();
//
//        System.out.println("Data is:= "+ data);
//
//
//    }


//    @Test
//    public void sms(){
//        SMSServices sms = new SMSServices();
//        String SMSServerCode;
//       String data =  sms.sendOtpSMS(Constants.smsUsername,
//                Constants.smsPassword, Constants.otp_Message +" "+ "131" + Constants.otp_MessageEnd,
//                Constants.smsSenderId,"9459619235",Constants.smsSecureKey, Constants.templateId);
//        System.out.println(data);
//
//        if (!data.isEmpty()) {
//            System.out.println(data);
//            SMSServerCode = data.split(",")[0];
//
//            if(SMSServerCode.equalsIgnoreCase("402")){
//                //Save to DB
//            }else{
//                //Save to DB no SMS
//            }
//
//        }else{
//            System.out.println("Some Error Occured");
//        }
//
//
//
//    }
}
