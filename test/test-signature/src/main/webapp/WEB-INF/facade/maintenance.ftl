<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--[if lte IE 9]>
    <script type="text/javascript" src="js/common/html5.js"></script>
    <![endif]-->

    <title>提示-页面维护中</title>
    <style type="text/css">
        *{margin:0;padding:0;}
        html {-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%;}
        body{-webkit-tap-highlight-color: rgba(0,0,0,0); font-family: "Microsoft Yahei", arial, sans-serif;}
        html, body{height:100%;}
        table{border-collapse:collapse;border-spacing:0;}
        .rear-wrap{
            width: 100%;
            height: 100%;
            min-width: 1200px;
            min-height:800px;
            position: relative;
        }
        .rearbg{
            width: 100%;
            height: 100%;
            display: block;
        }
        .mainten-overlay{
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            z-index: 9;
            display: table;
            background: transparent;
        }
        .mainten-table{
            display: table-cell;
            vertical-align: middle;
            text-align:center;
        }
        .mainten-wrap{
            width:646px;
            height:282px;
            padding:20px;
            display: inline-block;
            text-align:center;
            overflow: hidden;
            color:#fff;
            z-index: 11;
            background-color:rgba(0, 0, 0, .2);
        }
        .mainten-content{
            font-size: 18px;
            margin:30px 0;
        }
        .mainten-info{
            font-size: 30px;
        }
        @media \0screen\,screen\9{
            .mainten-wrap{
                background-color:#000000;
                -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=20);
                filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=20);
                position: static;
                *zoom:1;
            }
            .mainten-wrap div{
                position: relative;
            }

        }
        .mainten-title{
            margin:20px 0;
            text-align:center;
        }
        .maintenTil-bg{
            width:261px;
            height:60px;
            display: inline-block;
            background:transparent url("${host}/images/helloTxt.png") no-repeat;
        }
    </style>
</head>
<body>
<div class="rear-wrap">
    <img class="rearbg" src="${host}/images/mainten.jpg" alt="背景图">
    <div class="mainten-overlay">
        <div class="mainten-table">
            <div class="mainten-wrap">
                <div class="mainten-title">
                    <span class="maintenTil-bg"></span>
                    <div class="mainten-content">亲爱的用户，我们正在对网站进行维护中，给你带来不便，深表歉意。</div>
                    <div class="mainten-info">网页维护中......</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>