package com.samdev.payment_microservice.serviceImpl;

import com.samdev.payment_microservice.ContentGenOpenFeign.ConfirmStudentInfo;
import com.samdev.payment_microservice.ContentGenOpenFeign.StudentOpenFeignResponse;
import com.samdev.payment_microservice.Util.UniqueCodeGenerator;
import com.samdev.payment_microservice.entity.Subscription;
import com.samdev.payment_microservice.mpesa.MpesaTransact;
import com.samdev.payment_microservice.repository.SubscriptionRepository;
import com.samdev.payment_microservice.request.PaymentRequest;
import com.samdev.payment_microservice.response.PaymentResponse;
import com.samdev.payment_microservice.repository.PaymentRepository;
import com.samdev.payment_microservice.service.PaymentService;
import com.samdev.payment_microservice.stripe.CustomerUtil;
import com.samdev.payment_microservice.stripe.InitiatePayment;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

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

    @Value("${stripe.stripeApiKey}")
    private String stripeAPiKey;


    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final PaymentRepository paymentRepository;
    private final MpesaTransact mpesaTransact;
    private final ConfirmStudentInfo confirmStudentInfo;
    private final UniqueCodeGenerator uniqueCodeGenerator;
    private final CustomerUtil customerUtil;
    private final SubscriptionRepository subscriptionRepository;
    private final InitiatePayment initiatePayment;

    public PaymentServiceImpl(PaymentRepository paymentRepository, MpesaTransact mpesaTransact, ConfirmStudentInfo confirmStudentInfo, UniqueCodeGenerator uniqueCodeGenerator, CustomerUtil customerUtil, SubscriptionRepository subscriptionRepository, InitiatePayment initiatePayment) {
        this.paymentRepository = paymentRepository;
        this.mpesaTransact = mpesaTransact;
        this.confirmStudentInfo = confirmStudentInfo;
        this.uniqueCodeGenerator = uniqueCodeGenerator;
        this.customerUtil = customerUtil;
        this.subscriptionRepository = subscriptionRepository;
        this.initiatePayment = initiatePayment;
    }

    @Override
    public PaymentResponse initiatePayment(PaymentRequest paymentRequest) throws IOException, URISyntaxException, InterruptedException, StripeException {

        String phoneNumber = null;
        String email = null;
        String fullName = null;

        String accountReference = UniqueCodeGenerator.generateUniqueCode();

        if(paymentRequest.paymentOption().equalsIgnoreCase("mpesa")){

            if(paymentRequest.phoneNumber() == null || paymentRequest.phoneNumber().isEmpty()){
                //todo send a post request to student microservice to check whether the student exists

                StudentOpenFeignResponse studentById = confirmStudentInfo.findStudentById(paymentRequest.studentId());
                phoneNumber = studentById.phoneNumber();
                email = studentById.email();
                String firstName = studentById.firstName();
                String lastName = studentById.lastName();

                fullName = firstName + " "+lastName;


            } else{
                phoneNumber = paymentRequest.phoneNumber();

            }
            //todo implement mpesa daraja express payment request option
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
                        "200",
                        null
                );
            }

        }else{
            //todo implement stripe payment option
            Stripe.apiKey = stripeAPiKey;

            // Start by finding an existing customer record from Stripe or creating a new one if needed
            Customer customer = customerUtil.findOrCreateCustomer(email, fullName);

            Subscription subscription = subscriptionRepository.findById(1L).get();

            String s = initiatePayment.initiateToStripe(customer, subscription);

            log.info("This is generated payment url: {}", s);

            return new PaymentResponse(
                    "Success. Request accepted for processing",
                    "200",
                    s
            );


        }

        return null;

    }
}
