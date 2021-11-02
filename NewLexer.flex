package Jflextest;
import static Jflextest.Token.*;
%%
%class NewLexer
%type Token

L = [a-z]
D = [0-9]
white=[ \t\r\n]
LM = [A-Z]

%{
	public String lexeme;
%}
%%

{white} {/*Ignore*/}

{LM}{L}* ("_" ({L}{L} | {LM}{L}*))* {lexeme =yytext(); return NAME;}
( ( "("{D}{D}")" ) | {D}{D} )"-" {D}{D} "-" {D}{D} "-" {D}{D}	{lexeme =yytext(); return TELEPHONE;}
( {D}{D}{D}"-" | "(" {D}{D}{D} ")-" )* {D}{D}"-"{D}{D}"-"{D}{D}"-"{D}{D}"-"{D}{D} {lexeme =yytext(); return CELLPHONE;}
("www") ( "." ({L} | {D} | {LM} )+)+ (".com" | ".org") ( ("." | "-" | "%" | "/") ({L} | {D} | {LM} )+) * {lexeme=yytext(); return URL;}
{L} ({D} | {L} | {LM} | "." | "-" | "_")* "@" {L}+ ("." {L}+)+ {lexeme=yytext(); return EMAIL;}
("https://" | "http://") "www.youtube.com/watch?v=" ({D} | {L} | {LM} | "." | "-" | "%" | "/")* {lexeme=yytext(); return VIDEO;}

. {return ERROR;}