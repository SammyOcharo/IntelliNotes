package com.samdev.payment_microservice.serviceImpl;

import com.samdev.payment_microservice.ContentGenOpenFeign.ConfirmStudentInfo;
import com.samdev.payment_microservice.ContentGenOpenFeign.StudentOpenFeignResponse;
import com.samdev.payment_microservice.Util.UniqueCodeGenerator;
import com.samdev.payment_microservice.mpesa.MpesaTransact;
import com.samdev.payment_microservice.request.PaymentRequest;
import com.samdev.payment_microservice.response.PaymentResponse;
import com.samdev.payment_microservice.repository.PaymentRepository;
import com.samdev.payment_microservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${mpesa.clientId}")
    private String ClientId;

    @Value("${mpesa.ClientSecret}")
    private String ClientSecret;

    @Value("${mpesa.authUrl}")
    private String authUrl;

    @Value("${mpesa.businessShortCode}")
    public String businessShortCode;

    @Value("${mpesa.CallBackURL}")
    public  String callBackUrl;

    @Value("${mpesa.TransactionType}")
    public  String transactionType;

    @Value("${mpesa.passkey}")
    private String passkey;

    @Value("${mpesa.stkPushUrl}")
    private String requestURL;


    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final PaymentRepository paymentRepository;
    private final MpesaTransact mpesaTransact;
    private final ConfirmStudentInfo confirmStudentInfo;
    private final UniqueCodeGenerator uniqueCodeGenerator;

    public PaymentServiceImpl(PaymentRepository paymentRepository, MpesaTransact mpesaTransact, ConfirmStudentInfo confirmStudentInfo, UniqueCodeGenerator uniqueCodeGenerator) {
        this.paymentRepository = paymentRepository;
        this.mpesaTransact = mpesaTransact;
        this.confirmStudentInfo = confirmStudentInfo;
        this.uniqueCodeGenerator = uniqueCodeGenerator;
    }

    @Override
    public PaymentResponse initiatePayment(PaymentRequest paymentRequest) throws IOException, URISyntaxException, InterruptedException {


        log.info("End hit the service..");
        String phoneNumber = null;
        String accountReference = UniqueCodeGenerator.generateUniqueCode();

        if(paymentRequest.paymentOption().equalsIgnoreCase("mpesa")){

            if(paymentRequest.phoneNumber() == null || paymentRequest.phoneNumber().isEmpty()){
                //todo send a post request to student microservice to check whether the student exists

                StudentOpenFeignResponse studentById = confirmStudentInfo.findStudentById(paymentRequest.studentId());
                phoneNumber = studentById.phoneNumber();


            } else{
                phoneNumber = paymentRequest.phoneNumber();

            }
            //todo implement mpesa daraja express payment request option
            log.info("This is the number, {}", phoneNumber);

            if (MpesaTransact.initiateTransaction(
                    phoneNumber,
                    businessShortCode,
                    ClientId,
                    ClientSecret,
                    authUrl,
                    String.valueOf(paymentRequest.transactionAmount()),
                    callBackUrl,
                    transactionType,
                    requestURL,
                    passkey, accountReference
            )){

                return new PaymentResponse(
                        "Success. Request accepted for processing",
                        "200"
                );
            }

        }else{
            //todo implement stripe payment option
        }
        return null;
    }
}
