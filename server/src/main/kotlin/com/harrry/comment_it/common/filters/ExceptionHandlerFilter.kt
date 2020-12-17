package com.harrry.comment_it.common.filters

import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.fasterxml.jackson.databind.ObjectMapper
import com.harrry.comment_it.common.exceptions.InvalidAuthException
import com.harrry.comment_it.config.ErrorMessages
import com.harrry.comment_it.common.utils.GenericResponse
import com.harrry.comment_it.common.utils.ResponseHelper.sendErrorResponse
import com.harrry.comment_it.common.utils.StatusCode
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class ExceptionHandlerFilter: OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            filterChain.doFilter(request, response)
        } catch ( ex: SignatureVerificationException ) {
            sendErrorResponse(response,StatusCode.ERROR, ex.message.toString(), HttpStatus.FORBIDDEN)
        } catch ( ex: InvalidAuthException ) {
            sendErrorResponse(response, StatusCode.ERROR, ex.message.toString(), HttpStatus.FORBIDDEN)
        } catch ( ex: JWTDecodeException ) {
            sendErrorResponse(response, StatusCode.ERROR, ex.message.toString(), HttpStatus.FORBIDDEN)
        }
    }


}