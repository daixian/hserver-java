package com.dx.avserver.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "没找到这个番号...")
public class ExceptionNotFound extends RuntimeException{
}
