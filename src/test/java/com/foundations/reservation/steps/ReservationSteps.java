package com.foundations.reservation.steps;

import com.foundations.common.config.Configuration;
import com.foundations.common.config.TestConstants;
import com.foundations.common.model.AuthRequest;
import com.foundations.common.model.BookingDates;
import com.foundations.common.model.CreateBookingRequest;
import com.foundations.reservation.service.ReservationService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReservationSteps  {

    private final ReservationService reservationService;
    private final Configuration configuration;

    public ReservationSteps(ReservationService reservationService,
            Configuration configuration) {
        this.reservationService = reservationService;
        this.configuration = configuration;
    }

    @When("User receives authorisation key")
    public void getAuthorisation() {
        reservationService.getAuthKey(auth());
    }

    @Then("User can create a hotel reservation")
    public void createHotelReservation() {
        reservationService.createBooking(createBookingRequest());
    }

    @Then("User can cancel the hotel reservation")
    public void cancelHotelReservation() {
        reservationService.cancelHotelReservation();
    }

    @Then("Reservation created with the correct information")
    public void assertCreateReservation() {
        reservationService.assertBookingCreation();
    }

    private AuthRequest auth() {
        AuthRequest auth = new AuthRequest();
        auth.setUsername(configuration.getProperties().getProperty("username"));
        auth.setPassword(configuration.getProperties().getProperty("password"));
        return auth;
    }

    private CreateBookingRequest createBookingRequest() {
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setFirstname(TestConstants.firstName);
        createBookingRequest.setLastname(TestConstants.lastName);
        createBookingRequest.setTotalprice(TestConstants.totalPrice);
        createBookingRequest.setDepositpaid(TestConstants.depositPaid);
        createBookingRequest.setBookingdates(bookingDates());
        createBookingRequest.setAdditionalneeds(TestConstants.additionalNeeds);
        return createBookingRequest;
    }

    private BookingDates bookingDates() {
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2021-01-01");
        bookingDates.setCheckout("2022-01-01");
        return bookingDates;
    }

}
