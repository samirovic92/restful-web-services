package com.samirovic.restfulwebservices.versioning;

import com.samirovic.restfulwebservices.model.Name;
import com.samirovic.restfulwebservices.model.PersonV1;
import com.samirovic.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping(value = "/person/params", params = "version=1")
    public PersonV1 paramsV1(){

        return new PersonV1("Samir ELIDIRSSI");
    }

    @GetMapping(value = "/person/params", params = "version=2")
    public PersonV2 paramsV2(){

        return new PersonV2(new Name("Samir"," ELIDIRSSI"));
    }

    @GetMapping(value = "/person/headers", headers = "api-xi-version=1")
    public PersonV1 headerV1(){

        return new PersonV1("Samir ELIDIRSSI");
    }

    @GetMapping(value = "/person/headers", headers = "api-xi-version=2")
    public PersonV2 headerV2(){

        return new PersonV2(new Name("Samir"," ELIDIRSSI"));
    }

}
