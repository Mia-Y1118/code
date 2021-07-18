package com.exam.home.dailytest

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.exam.home.dailytest.updateUI.UpdateUIActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val imgStr =
        "<p>从大众传播和非大众传播、新闻活动和非新闻活动这两个视角出发，可得出如下结论A=新闻事业。由此，传播学、大众传播学和新闻学这3门学科关系，以及它们各自的研究对象，也就明确了。即传播学=ABCD,大众传播学=AB,新闻学=AC(以A为主)。&nbsp;<br></p ><p>< img src=\"http://store.sunlands.com/qiyejia/original/20180131/1517392420542.jpg\" alt=\"\" width=\"500\" height=\"175\"><br></p >"
    val blankStr =
        "<p>English lexicology is chiefly concerned with the basic <img src=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAABBJREFUeNpi+P//PwNAgAEACPwC/tuiTRYAAAAASUVORK5CYII= class=__blank__placeholder>of words in general and of English words in particular.</p>"


    val baseStr =
        "        \"<p>English lexicology is chiefly concerned with the basic <img src=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAABBJREFUeNpi+P//PwNAgAEACPwC/tuiTRYAAAAASUVORK5CYII= >of words in general and of English words in particular.</p>\"\n"

    val htmlData = "<!DOCTYPE html>\n" +
            "    <html>\n" +
            "    <head>\n" +
            "    \t<title>模考</title>\n" +
            "    \t<meta charset=\"utf-8\">\n" +
            "    \t<meta name=\"viewport\" content=\"width=device-width, target-densitydpi=device-dpi, user-scalable=no\">\n" +
            "    \t<script type=\"text/javascript\">\n" +
            "    \t\tfunction callJS(){var box = document.getElementsByClassName(\"__blank__placeholder\");var array = [];for (var i = box.length - 1; i >= 0; i--) {var pos = box[i].getBoundingClientRect();var txt1 = {\"top\":pos.top,\"left\":pos.left,\"bottom\":pos.bottom,\"right\":pos.right,\"width\":pos.width,\"height\":pos.height};array[i]=txt1;}return array;}\n" +
            "    \t\tvar dWidth = 411;\n" +
            "    \t\tdocument.documentElement.style.fontSize = dWidth/16 + 'px';\n" +
            "    \t</script>\n" +
            "    \t<style>\n" +
            "    \thtml,div,p{\n" +
            "    \t\tmargin:0;\n" +
            "    \t\tpadding:0;\n" +
            "    \t}\n" +
            "    .__blank__placeholder{\n" +
            "    \twidth:5.1rem;\n" +
            "    \theight:.683rem;\n" +
            "    \tborder-bottom:.064rem solid rgb(50%,50%,50%);\n" +
            "    \n" +
            "    }\n" +
            "    img{max-width:14px;}\n" +
            "    body{\n" +
            "    \tfont-size: .577rem;\n" +
            "    \tline-height: 1.5em;\n" +
            "    \tpadding:.448rem .64em 0;\n" +
            "    \tcolor: \\#323232;\n" +

            "    }\n" +
            "    </style>\n" +
            "    </head>\n" +
            "    <body><p>某人某年年初存入银行2000元，年利率3% ,按复利方法计算，第三年年末他可以得到本利和（ ）</p></body></html>";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_img.loadContent(imgStr)
        tv_blank.setOnClickListener {
            startActivity(Intent(this, UpdateUIActivity::class.java))
        }
        tv_blank.text = Html.fromHtml(blankStr)

//        tv_rich.loadData(htmlData,"text/html; charset=UTF-8", null)
        tv_rich.settings.javaScriptEnabled = true
        tv_rich.loadUrl("https://www.baidu.com/")

//        tv_rich.loadUrl("about:blank")
    }
}
