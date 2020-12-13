package com.example.zhihudaily.Models

data class ZhihuDaily (
    val date:String,
    val stories:Array<Story>,
    val top_stories:Array<Top_Story>
)

data class ZhihuDaily_Before (
    val date:String,
    val stories:Array<Story>
)
//"body": "<div class=\"main-wrap content-wrap\">\n<div class=\"headline\">\n\n<div class=\"img-place-holder\"><\/div>\n\n\n\n<\/div>\n\n<div class=\"content-inner\">\n\n\n\n\n<div class=\"question\">\n<h2 class=\"question-title\"><\/h2>\n\n<div class=\"answer\">\n\n<div class=\"meta\">\n<img class=\"avatar\" src=\"https:\/\/pic2.zhimg.com\/v2-626cfb8198a42af98a7bca04e3fdfc73_l.jpg?source=8673f162\">\n<span class=\"author\">康石石，<\/span><span class=\"bio\">艺术留学大拿<\/span>\n<a href=\"https:\/\/www.zhihu.com\/question\/41094862\/answer\/1569381024\" class=\"originUrl\" hidden>查看知乎原文<\/a>\n<\/div>\n\n<div class=\"content\">\n<p>说起男装女装不同肯定是有的，但总体来说却又是一致的，下面咱们稍作分析。<\/p>\r\n<p>既然男装、女装同属于服装设计，那么在总体方向和设计主旨上自然是一样的，也一样都依据相同的设计流程——即概念 - 调研 - 设计 - 产出四大步骤。那么细看每一步呢？其实核心要求也都是一样的，主要的差异体现在具体的设计上，那么具体是哪些设计的点不同呢？我给大家总结为版型剪裁、工艺侧重和服装品类这三个设计点的不同：<\/p>\r\n<p><strong>1.版型剪裁不同<\/strong><\/p>\r\n<p>为什么第一点是版型剪裁的不同呢？我们首先要明白，服装设计的版型剪裁都是根据人体结构来设计的，女性和男性的人体结构是不同的，所以男装和女装的版型剪裁侧重自然也不同。<\/p>\r\n<figure><img class=\"content-image\" src=\"https:\/\/pic4.zhimg.com\/v2-9b3a413801d4030aa412d2a4e7956fe7_720w.jpg?source=8673f162\" alt=\"\"><figcaption>图源网络<\/figcaption><\/figure><p>从示意图我们可以不难看出，女性人体体块是呈正等腰三角形的结构，而男性人体体快是呈倒等腰三角形的结构，因此女装的版型剪裁会更加偏柔和，而男装的版型剪裁更强调体块感和力量感；除此以外女性的身体线条多为曲线，而男性身体线条多为直线。因此女装的版型剪裁也更加修身，更多强调身体的曲线美，而男装版型则多为直线。<\/p>\r\n<p>因此最为直观的，在制版这一步，设计师就会结合这样的人体生理知识去区分男女装的版型剪裁设计，这是第一点不同。<\/p>\r\n<p><strong>2.工艺侧重不同<\/strong><\/p>\r\n<p>除了版型剪裁的不同，第二点比较突出的就是男女装工艺侧重的不同，这一点其实会涉及到人类社会中男女分工角色的差异。<\/p>\r\n<p>由于男性体能上的优势，从原始时期开始基本都是由男性来从事体力劳动，比如说中国传统农业社会的「男耕女织」模式，因此男装自古以来就十分注重功能性，各个国家的狩猎服、工装等等都是由此出现，并延续到当代男装的设计中；而由于女性爱美的天性，加上女性几乎不用从事重体力劳动，因此女装大多强调装饰性和美观性，比如说中国古代女装的刺绣、西方维多利亚时期繁复的蕾丝边等等，这些装饰性元素也延续到了当代女装的设计中。<\/p>\r\n<figure><img class=\"content-image\" src=\"https:\/\/pic4.zhimg.com\/v2-89c024f70d8ba56c81b58a4f522201f9_720w.jpg?source=8673f162\" alt=\"\"><figcaption>历史上的男性工装<\/figcaption><\/figure><figure><img class=\"content-image\" src=\"https:\/\/pic2.zhimg.com\/v2-f6dd2659d21aa34418531a0d34d3a253_720w.jpg?source=8673f162\" alt=\"\"><figcaption>维多利亚时期的女性穿着 - Vintage Photography - Photo Trading Cards Set<\/figcaption><\/figure><p>因此对于设计师而言，男装会比较注重对服装工艺的掌握程度，同学们在学习的过程中也会更加偏向于研究男装的内在结构以及结构方式，而女装要更多展示我们的创意性、多变性。<\/p>\r\n<p>这点咱们通过我们学生创作的男装&amp;女装作品集，就能非常清晰的看出差别。<\/p>\r\n<figure><img class=\"content-image\" src=\"https:\/\/pic2.zhimg.com\/v2-2ff9cf4bf2ff92616301bf953bb5f7c9_720w.jpg?source=8673f162\" alt=\"\"><figcaption>w 同学，成功申请 RCA、Parsons 等学校男装 ma<\/figcaption><\/figure><figure><img class=\"content-image\" src=\"https:\/\/pic4.zhimg.com\/v2-92166067fc6360ebd6a3cce09828f84f_720w.jpg?source=8673f162\" alt=\"\"><figcaption>汉艺 m 同学 成功申请金斯顿 南安普顿大学女装 ma<\/figcaption><\/figure><p><strong>3.服装品类不同<\/strong><\/p>\r\n<p>男女装设计的第三点不同就是服装品类的不同。咱们拿不同的世界名校来举例，比如男装 top 热门院校威斯敏斯特大学的 MA 课程就极其强调对于历史上不同种类男装的了解，比如说马术服、机车夹克、军装、飞行员夹克、西服、航海服等等各种品类的男装，我们在做 research 的时候就需要去调研历史上众多不同男装的结构与款式。<\/p>\r\n<figure><img class=\"content-image\" src=\"https:\/\/pic1.zhimg.com\/v2-51e12baa260c56c0b762929653a013f2_720w.jpg?source=8673f162\" alt=\"\"><figcaption>威斯敏斯特大学官网 menswear archive，可以看到罗列了历史上各种男装品类<\/figcaption><\/figure><p>而女装也有各种不一样的品类，比如维多利亚时期有巨大裙撑的礼服裙、爱德华时期的女士西服套装、女仆装等等，这些历史相关的服装品类都是我们同学需要去掌握的重点，有了丰厚的学术基底才能设计出既当代又专业的设计作品。<\/p>\r\n<p>这里我想特别强调这个问题，很多希望学习服装设计的同学在刚刚接触的时候会把很多的精力放在前期天马行空的社会调研和装置试验中，觉得做的很炸就能拿到世界名校 offer，获得更多人的认可。但其实完全不知道自己丢失了 fashion research，这样就会导致最终做出来的衣服「空而无物」，用了一堆的实验材料想要表达概念，但做出来一个「四不像」或者效果很 costume。这一点在男装设计上尤为明显，男装是一定需要 fashion research 的基础知识支撑的，所以同学们一定要加强「服装品类」这样的学术概念。<\/p>\r\n<p>以上就是男女装设计的 3 个不同之处，其实是比较传统的分类方式了，但我们可以注意到，当代男女装设计「去性别」的趋势近年来也越来越明显，很多品牌男女装不再分开走秀，很多男装设计也是逐渐阴柔、女性化，而女装设计愈发中性、帅气。在这样的趋势下，我们同学在做服装设计的时候也有了更多的选择权利。<\/p>\r\n<figure><img class=\"content-image\" src=\"https:\/\/pic1.zhimg.com\/v2-8c163f7781ef2352aca336668db99999_720w.jpg?source=8673f162\" alt=\"\"><figcaption>各大品牌男装秀场越来越「娘」图源：comme des garcons、Ann Demeulemeester 秀场<\/figcaption><\/figure><p>在这样 unisex 的大趋势下，同学们可以更自由、更深入地去探讨男性气质与女性气质，也不必局限于传统的男女装分类，但这不代表我们就可以丢失掉上文三个重要的设计点。<\/p>\r\n<p>我们从传统男女装分类的三个设计点中可以得到的启发是，<strong>第一<\/strong>，服装设计的版型剪裁需要契合人体，并非凭空而来，切勿一味玩概念而丢掉剪裁的基础知识；<strong>第二<\/strong>，工艺是我们设计的支撑，工艺也从历史中而来，懂得基础工艺才能有好的创新；<strong>第三<\/strong>，服装品类始终是学生做设计中容易忽视却极其重要的一个部分，缺失 fashion research 属于原则性的学术错误。<\/p>\r\n<p>总的来说，<strong>我们要更多更深入地去了解男装和女装不同的历史发展和设计原理，而不是粗暴地将男装设计女装设计划分开<\/strong>。女装 vs 男装作品集的不同绝不是浮于表面的形式上的不同，绝不是简单的男装注重工艺、女装变化丰富就行，同学们一定要要透过这些表面现象多去学习、思考服装背后的种种历史发展与设计原理。服装设计其实是很具有深度、很复杂的一门学科，当我们的思考足够深入、知识量够宽广的时候，我们才能真正从学生蜕变为一个成熟的服装设计师。<\/p>\r\n<p>以上，望有帮助<\/p>\n<\/div>\n<\/div>\n\n\n<div class=\"view-more\"><a href=\"https:\/\/www.zhihu.com\/question\/41094862\">查看知乎讨论<span class=\"js-question-holder\"><\/span><\/a><\/div>\n\n<\/div>\n\n\n<\/div>\n<\/div><script type=“text\/javascript”>window.daily=true<\/script>",
//"image_hue": "0xb39e74",
//"image_source": "markusspiske \/ CC0",
//"title": "男装跟女装的不同在哪里？",
//"url": "https:\/\/daily.zhihu.com\/story\/9729913",
//"image": "https:\/\/pic2.zhimg.com\/v2-9e64afec81ef4df7d93ec5a00471f766.jpg?source=8673f162",
//"share_url": "http:\/\/daily.zhihu.com\/story\/9729913",
//"js": [],
//"ga_prefix": "111407",
//"images": [
//"https:\/\/pic1.zhimg.com\/v2-989a8ba2061bc37e135e909f6f717a7f.jpg?source=8673f162"
//],
//"type": 0,
//"id": 9729913,
//"css": [
//"http:\/\/news-at.zhihu.com\/css\/news_qa.auto.css?v=4b3e3"
//]
data class Page_Story(
    val body:String,
    val title:String,
    val image:String,
    val css:Array<String>
)

data class Story(
    val title:String,
    val url:String,
    val hint:String,
    val images:Array<String>,
    val type:Int,
    val id:Int
)

data class Top_Story(
    val title:String,
    val url:String,
    val hint:String,
    val image:String,
    val type:Int,
    val id:Int
)