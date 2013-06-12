//Фреймворк Мурзилка версия 1.0

Lister = function(target, pos)                          //Для списка с сылками
{
    var items = target.getElementsByTagName('a');
        target.style.visibility = '';        
    for(i=0; i<items.length; i++)
    {
        items[i].style.position = 'relative';
        items[i].style.opacity = '0.1';    
        items[i].style.top = pos + 'px';
        
        if(pos<0)
        {
            FadedDown(items[i]);
        }
        else
        {
            FadedUp(items[i]);
        }
        
    }
}

FadedDown = function(item)                              //Для Lister
{
    
    setTimeout(function()
               {
                var Top = CutPx(item.style.top);
                
                if(navigator.appName != 'Microsoft Internet Explorer')
                {
                    var Opa = CutPx(item.style.opacity);
                }

                if(Top<0)
                {
                    item.style.top = Top+1;
                    item.style.opacity = Opa+0.1;
                    FadedDown(item);
                }
                
                }, 10);
}

FadedUp = function(item)                              //Для Lister
{
    
    setTimeout(function()
               {
                var Top = CutPx(item.style.top);
                
                if(navigator.appName != 'Microsoft Internet Explorer')
                {
                    var Opa = CutPx(item.style.opacity);
                }
                
                if(Top>0)
                {
                    item.style.top = Top-1;
                    item.style.opacity = Opa+0.1;
                    FadedUp(item);
                }
                
                }, 1);
}


TextMachine = function(target, speed)               //Эффект печатающей машинки
{
    target.style.visibility = '';
    
    if(navigator.appName == 'Microsoft Internet Explorer')
    {
        var txt = target.innerText;
            target.innerText = '';
    }
    else
    {
        var txt = target.innerHTML;
            target.innerHTML = '';
    }
    
    
    
    TextPrint(target, txt, speed);
}

TextPrint = function(target, txt, speed)        //Для TextMachine
{

    setTimeout(function()
               {
                if(navigator.appName == 'Microsoft Internet Explorer')
                {
                    txt = txt.toArray();
                    var countTarget = target.innerText.length;
                }
                else
                {
                    var countTarget = target.innerHTML.length;
                }

                if(countTarget < txt.length)
                {    
                        if(navigator.appName == 'Microsoft Internet Explorer')
                        {
                            target.innerText += txt[countTarget];
                        }
                        else
                        {
                            target.innerHTML += txt[countTarget];
                        }
                        
                    TextPrint(target, txt, speed);
                }
               }, speed);
}

Tremore = function(target, speed)           //Перевод звучит как "Дрожь"
{
    setTimeout(function()
               {
                
                target.style.position = 'relative';
                target.style.top = '2px';
                target.style.left = '-2px';
                
                setTimeout(function()
                           {
                            
                            target.style.top = '3px';
                            target.style.left = '2px';
                            
                            setTimeout(function()
                                       {
                                        
                                        target.style.top = '-2px';
                                        target.style.left = '-2px';
                                        
                                        setTimeout(function()
                                                   {
                                                    
                                                    target.style.position = '';
                                                    
                                                   }, speed);
                                        
                                       }, speed);    
                           }, speed);
                
                
               }, speed);
}
  
  


ChangePos = function(target, direction, speed, size)                    //Плавно двигает объект в указаном направление top, left, right, bottom
{
    target.style.position = 'relative';
    
    setTimeout(function()
               {
                switch(direction)
                    {
                        case 'top' :
                            {
                                var pos = CutPx(target.style.top);
                
                                if(pos > -size)
                                    {
                                        pos--;
                                        target.style.top = pos + 'px';    
                                    }
                                ChangePos(target, direction, speed, size);
                                break;
                            }
                            
                        case 'right' :
                            {
                                var pos = CutPx(target.style.left);
                
                                if(pos < size)
                                    {
                                        pos++;
                                        target.style.left = pos + 'px';    
                                    }
                                ChangePos(target, direction, speed, size);
                                break;
                            }
                        
                        case 'bottom' :
                            {
                                var pos = CutPx(target.style.top);
                
                                if(pos < size)
                                    {
                                        pos++;
                                        target.style.top = pos + 'px';    
                                    }
                                ChangePos(target, direction, speed, size);
                                break;
                            }
                            
                        case 'left' :
                            {
                                var pos = CutPx(target.style.left);
                                
                                if(pos > -size)
                                    {
                                        pos--;
                                        target.style.left = pos + 'px';    
                                    }
                                ChangePos(target, direction, speed, size);
                                break;
                            }
                    }    
               }, speed);
    
    
}
  
  
StartSnow = function(beetwen)
{
    setInterval(function(){Snow();}, beetwen);
}

Snow = function()
{
    var pos = Math.random()*1500;
    var SnowBall = document.createElement('span');
        SnowBall.innerHTML = '*';
        SnowBall.style.position = 'absolute';
        SnowBall.style.color = '#b2ccfc';
        SnowBall.style.top = '0px';
        SnowBall.style.left = pos + 'px';
        SnowBall.style.fontSize = '25px';
        
        document.body.appendChild(SnowBall);
        
        var xinterval = setInterval(function(){SnowDown(SnowBall, xinterval);},100);
}

SnowDown = function(SnowBall, xinterval)
{
    var Top = CutPx(SnowBall.style.top);
    
    if(Top<670)
    {
        Top++;
        SnowBall.style.top = Top;
    }
    else
    {
        SnowBall.remove();
        clearInterval(xinterval);
    }
}


BigWord = function(word, e, target)
{
    var W = document.createElement('span');
        W.style.fontSize = '300px';
        W.style.color = '999999';
        W.style.opacity = 1;
        W.innerHTML = word;
        target.parentNode.appendChild(W);
        var xinterval = setInterval(function(){ResizeFade(W, xinterval)}, 100);
        
        
}

ResizeFade = function(W, xinterval)
{
    var font = CutPx(W.style.fontSize);
    var opa = W.style.opacity;
    if(font>0)
    {
        font-=25;
        opa-=0.1;
        W.style.fontSize = font + 'px';
        W.style.opacity = opa;
    }
    else
    {
        W.remove();
        clearInterval(xinterval);
    }
}



GetKey = function(e, target)
{
    e = e || window.event;
    
    if(e.charCode == 0)
    {
        
    }
    else
    {
        if(navigator.appName == 'Netscape')
        {
            BigWord(ReturnKey(e.charCode), e, target);
        }
        else
        {
            BigWord(ReturnKey(e.keyCode), e, target);
        }
        
    }
    
    
}

ReturnKey = function(num)
{
    switch(num)
    {
        //Расскладка EN
        case 32 : { return ' '; break;}
        case 33 : { return '!'; break;}
        case 34 : { return '"'; break;}
        case 35 : { return '#'; break;}
        case 36 : { return '$'; break;}
        case 37 : { return '%'; break;}
        case 38 : { return '&'; break;}
        case 39 : { return "'"; break;}
        case 40 : { return '('; break;}
        case 41 : { return ')'; break;}
        case 42 : { return '*'; break;}
        case 43 : { return '+'; break;}
        case 44 : { return ','; break;}
        case 45 : { return '-'; break;}
        case 46 : { return '.'; break;}
        case 47 : { return '/'; break;}
        
        
        case 48 : { return '0'; break;}
        case 49 : { return '1'; break;}
        case 50 : { return '2'; break;}
        case 51 : { return '3'; break;}
        case 52 : { return '4'; break;}
        case 53 : { return '5'; break;}
        case 54 : { return '6'; break;}
        case 55 : { return '7'; break;}
        case 56 : { return '8'; break;}
        case 57 : { return '9'; break;}
        
        case 59 : { return ';'; break;}
        case 58 : { return ':'; break;}
        case 60 : { return '<'; break;}
        case 61 : { return '='; break;}
        case 62 : { return '>'; break;}
        case 63 : { return '?'; break;}
        case 64 : { return '@'; break;}
       
        case 65 : { return 'A'; break;}
        case 66 : { return 'B'; break;}
        case 67 : { return 'C'; break;}
        case 68 : { return 'D'; break;}
        case 69 : { return 'E'; break;}    
        case 70 : { return 'F'; break;}
        case 71 : { return 'G'; break;}
        case 72 : { return 'H'; break;}
        case 73 : { return 'I'; break;}
        case 74 : { return 'J'; break;}
        case 75 : { return 'K'; break;}
        case 76 : { return 'L'; break;}
        case 77 : { return 'M'; break;}
        case 78 : { return 'N'; break;}
        case 79 : { return 'O'; break;}
        case 80 : { return 'P'; break;}
        case 81 : { return 'Q'; break;}
        case 82 : { return 'R'; break;}
        case 83 : { return 'S'; break;}
        case 84 : { return 'T'; break;}
        case 85 : { return 'U'; break;}
        case 86 : { return 'V'; break;}
        case 87 : { return 'W'; break;}
        case 88 : { return 'X'; break;}
        case 89 : { return 'Y'; break;}
        case 90 : { return 'Z'; break;}
        
        case 92 : { return '\\'; break;}
        case 94 : { return '^'; break;}
        case 95 : { return '_'; break;}
        case 96 : { return '`'; break;}
        
        case 97 : { return 'a'; break;}
        case 98 : { return 'b'; break;}
        case 99 : { return 'c'; break;}
        case 100 : { return 'd'; break;}
        case 101 : { return 'e'; break;}    
        case 102 : { return 'f'; break;}
        case 103 : { return 'g'; break;}
        case 104 : { return 'h'; break;}
        case 105 : { return 'i'; break;}
        case 106 : { return 'j'; break;}
        case 107 : { return 'k'; break;}
        case 108 : { return 'l'; break;}
        case 109 : { return 'm'; break;}
        case 110 : { return 'n'; break;}
        case 111 : { return 'o'; break;}
        case 112 : { return 'p'; break;}
        case 113 : { return 'q'; break;}
        case 114 : { return 'r'; break;}
        case 115 : { return 's'; break;}
        case 116 : { return 't'; break;}
        case 117 : { return 'u'; break;}
        case 118 : { return 'v'; break;}
        case 119 : { return 'w'; break;}
        case 120 : { return 'x'; break;}
        case 121 : { return 'y'; break;}
        case 122 : { return 'z'; break;}
            
        case 124 : { return '|'; break;}
        case 126 : { return '~'; break;}
            
        //Раскладка RU
        
        case 1025 : { return 'Ё'; break;}
        case 1040 : { return 'А'; break;}
        case 1041 : { return 'Б'; break;}
        case 1042 : { return 'В'; break;}
        case 1043 : { return 'Г'; break;}
        case 1044 : { return 'Д'; break;}    
        case 1045 : { return 'Е'; break;}
        case 1046 : { return 'Ж'; break;}
        case 1047 : { return 'З'; break;}
        case 1048 : { return 'И'; break;}
        case 1049 : { return 'Й'; break;}    
        case 1050 : { return 'К'; break;}
        case 1051 : { return 'Л'; break;}
        case 1052 : { return 'М'; break;}
        case 1053 : { return 'Н'; break;}
        case 1054 : { return 'О'; break;}
        case 1055 : { return 'П'; break;}
        case 1056 : { return 'Р'; break;}
        case 1057 : { return 'С'; break;}
        case 1058 : { return 'Т'; break;}
        case 1059 : { return 'У'; break;}
        case 1060 : { return 'ф'; break;}
        case 1061 : { return 'Х'; break;}
        case 1062 : { return 'Ц'; break;}
        case 1063 : { return 'Ч'; break;}
        case 1064 : { return 'Ш'; break;}
        case 1065 : { return 'Щ'; break;}
        case 1066 : { return 'Ъ'; break;}
        case 1067 : { return 'Ы'; break;}
        case 1068 : { return 'Ь'; break;}
        case 1069 : { return 'Э'; break;}
        case 1070 : { return 'Ю'; break;}
        case 1071 : { return 'Я'; break;}

        case 1072 : { return 'а'; break;}
        case 1073 : { return 'б'; break;}
        case 1074 : { return 'в'; break;}
        case 1075 : { return 'г'; break;}
        case 1076 : { return 'д'; break;}    
        case 1077 : { return 'е'; break;}
        case 1078 : { return 'ж'; break;}
        case 1079 : { return 'з'; break;}
        case 1080 : { return 'и'; break;}
        case 1081 : { return 'й'; break;}    
        case 1082 : { return 'к'; break;}
        case 1083 : { return 'л'; break;}
        case 1084 : { return 'м'; break;}
        case 1085 : { return 'н'; break;}
        case 1086 : { return 'о'; break;}
        case 1087 : { return 'п'; break;}
        case 1088 : { return 'р'; break;}
        case 1089 : { return 'с'; break;}
        case 1090 : { return 'т'; break;}
        case 1091 : { return 'у'; break;}
        case 1092 : { return 'ф'; break;}
        case 1093 : { return 'х'; break;}
        case 1094 : { return 'ц'; break;}
        case 1095 : { return 'ч'; break;}
        case 1096 : { return 'ш'; break;}
        case 1097 : { return 'щ'; break;}
        case 1098 : { return 'ъ'; break;}
        case 1099 : { return 'ы'; break;}
        case 1100 : { return 'ь'; break;}
        case 1101 : { return 'э'; break;}
        case 1102 : { return 'ю'; break;}
        case 1103 : { return 'я'; break;}
        case 1105 : { return 'ё'; break;}
            
        case 8470 : { return '#'; break;}
        
        default : {return 'HZ'; break;}
    }
}

CutPx = function(target)                        //Отризает px при получение значения атрибута, возвращая только числовое значение.
{
    var result = target.split('px');
        result[0]++;
        result[0]--;
        
    return result[0];
}

Absolute = function(num)
{
    if(num>0)
    {
        return num;
    }
    else
    {
        return num*(-1);
    }
}



