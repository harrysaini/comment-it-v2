package com.harrry.comment_it.common.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class StaticFileHandlingController: ErrorController {

    @RequestMapping("/error")
    fun error(req: HttpServletRequest, res: HttpServletResponse): Any {
        return if (req.method.equals(HttpMethod.GET.name, true) && !req.contextPath.contains("api", true)) {
            "forward:index.html"
        } else {
            ResponseEntity.notFound().build<Unit>()
        }
    }
    override fun getErrorPath(): String {
        return "/error"
    }
}