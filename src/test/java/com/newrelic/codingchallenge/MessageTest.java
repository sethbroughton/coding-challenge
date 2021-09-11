package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.Model.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MessageTest {

    //Happy Test
    @Test
    public void test() {

        Message message = new Message("000000000");
        assertTrue(message.isValid());
    }
}