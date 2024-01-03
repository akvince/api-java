package com.tuto.api.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
@Api(tags = "Brands Controller", description = "Operations pertaining to brands")
public class BrandController {
}
