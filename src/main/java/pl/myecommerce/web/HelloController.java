package pl.myecommerce.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@RestController
public class HelloController {
    @GetMapping("/hello")
    String hello(){
        return "hello world";
    }
    @GetMapping("/example")
    String whatsup(){
        return "<html>\n" +
                "<head>\n" +
                "    <title>My ebook store</title>\n" +
                "    <link rel=\"stylesheet\" href=\"/css/main.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<header>\n" +
                "    <div class=\"cart\">\n" +
                "        <span class=\"total\">...</span>\n" +
                "        <span class=\"itemsCount\">...</span>\n" +
                "        <button>BUY</button>\n" +
                "    </div>\n" +
                "</header>\n" +
                "<h1>My ebook store :)</h1>\n" +
                "<ul id=\"products-list\"></ul>\n" +
                "<script type=\"text/javascript\" src=\"/scripts/index.js\"></script>\n" +
                "</body>\n" +
                "</html>";
    }

}
