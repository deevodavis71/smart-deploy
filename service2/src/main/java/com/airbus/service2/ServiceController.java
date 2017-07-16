package com.airbus.service2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api")
public class ServiceController
{
    @GetMapping ("/hostname")
    public ResponseEntity<String> getHostname () throws UnknownHostException
    {
        // Get the hostname
        InetAddress ip = InetAddress.getLocalHost();
        String hostname = ip.getHostName ();

        // Return the hostname
        return new ResponseEntity<String> ("Service2: Hello World from - " + hostname, HttpStatus.OK);
    }
}