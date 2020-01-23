let SessionLoad = 1
let s:so_save = &so | let s:siso_save = &siso | set so=0 siso=0
let v:this_session=expand("<sfile>:p")
silent only
cd ~/class/zissen/basic
if expand('%') == '' && !&modified && line('$') <= 1 && getline(1) == ''
  let s:wipebuf = bufnr('%')
endif
set shortmess=aoO
badd +6 newlang4/Function.java
badd +9 newlang4/PrintFunction.java
badd +0 term://.//17631:/home/linuxbrew/.linuxbrew/bin/zsh
badd +0 newlang4/Program.java
badd +0 newlang4/StmtList.java
badd +36 newlang4/Node.java
badd +54 newlang4/Stmt.java
badd +0 newlang4/CallSub.java
badd +0 newlang4/Environment.java
badd +230 newlang4/LexicalAnalyzerImpl.java
argglobal
%argdel
$argadd newlang4/Function.java
set stal=2
edit newlang4/Program.java
set splitbelow splitright
wincmd _ | wincmd |
vsplit
1wincmd h
wincmd _ | wincmd |
split
1wincmd k
wincmd w
wincmd w
set nosplitbelow
set nosplitright
wincmd t
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
exe '1resize ' . ((&lines * 23 + 24) / 48)
exe 'vert 1resize ' . ((&columns * 95 + 95) / 191)
exe '2resize ' . ((&lines * 21 + 24) / 48)
exe 'vert 2resize ' . ((&columns * 95 + 95) / 191)
exe 'vert 3resize ' . ((&columns * 95 + 95) / 191)
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=100
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 48 - ((22 * winheight(0) + 11) / 23)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
48
normal! 0
wincmd w
argglobal
if bufexists("newlang4/StmtList.java") | buffer newlang4/StmtList.java | else | edit newlang4/StmtList.java | endif
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=100
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 65 - ((19 * winheight(0) + 10) / 21)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
65
normal! 0
wincmd w
argglobal
if bufexists("term://.//17631:/home/linuxbrew/.linuxbrew/bin/zsh") | buffer term://.//17631:/home/linuxbrew/.linuxbrew/bin/zsh | else | edit term://.//17631:/home/linuxbrew/.linuxbrew/bin/zsh | endif
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=100
setlocal fml=1
setlocal fdn=20
setlocal fen
let s:l = 386 - ((44 * winheight(0) + 22) / 45)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
386
normal! 050|
wincmd w
3wincmd w
exe '1resize ' . ((&lines * 23 + 24) / 48)
exe 'vert 1resize ' . ((&columns * 95 + 95) / 191)
exe '2resize ' . ((&lines * 21 + 24) / 48)
exe 'vert 2resize ' . ((&columns * 95 + 95) / 191)
exe 'vert 3resize ' . ((&columns * 95 + 95) / 191)
tabedit newlang4/Stmt.java
set splitbelow splitright
wincmd _ | wincmd |
vsplit
1wincmd h
wincmd _ | wincmd |
split
1wincmd k
wincmd w
wincmd w
set nosplitbelow
set nosplitright
wincmd t
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
exe '1resize ' . ((&lines * 22 + 24) / 48)
exe 'vert 1resize ' . ((&columns * 95 + 95) / 191)
exe '2resize ' . ((&lines * 22 + 24) / 48)
exe 'vert 2resize ' . ((&columns * 95 + 95) / 191)
exe 'vert 3resize ' . ((&columns * 95 + 95) / 191)
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=100
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 3 - ((2 * winheight(0) + 11) / 22)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
3
normal! 0
wincmd w
argglobal
if bufexists("newlang4/Environment.java") | buffer newlang4/Environment.java | else | edit newlang4/Environment.java | endif
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=100
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 1 - ((0 * winheight(0) + 11) / 22)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
1
normal! 0
wincmd w
argglobal
if bufexists("newlang4/CallSub.java") | buffer newlang4/CallSub.java | else | edit newlang4/CallSub.java | endif
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=100
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 60 - ((40 * winheight(0) + 22) / 45)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
60
normal! 020|
wincmd w
exe '1resize ' . ((&lines * 22 + 24) / 48)
exe 'vert 1resize ' . ((&columns * 95 + 95) / 191)
exe '2resize ' . ((&lines * 22 + 24) / 48)
exe 'vert 2resize ' . ((&columns * 95 + 95) / 191)
exe 'vert 3resize ' . ((&columns * 95 + 95) / 191)
tabedit newlang4/PrintFunction.java
set splitbelow splitright
wincmd _ | wincmd |
split
1wincmd k
wincmd w
set nosplitbelow
set nosplitright
wincmd t
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
exe '1resize ' . ((&lines * 22 + 24) / 48)
exe '2resize ' . ((&lines * 22 + 24) / 48)
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=100
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 11 - ((10 * winheight(0) + 11) / 22)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
11
normal! 014|
wincmd w
argglobal
if bufexists("newlang4/Function.java") | buffer newlang4/Function.java | else | edit newlang4/Function.java | endif
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=100
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 8 - ((7 * winheight(0) + 11) / 22)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
8
normal! 0
wincmd w
exe '1resize ' . ((&lines * 22 + 24) / 48)
exe '2resize ' . ((&lines * 22 + 24) / 48)
tabnext 1
set stal=1
if exists('s:wipebuf') && getbufvar(s:wipebuf, '&buftype') isnot# 'terminal'
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20 winminheight=1 winminwidth=1 shortmess=filnxtToOF
let s:sx = expand("<sfile>:p:r")."x.vim"
if file_readable(s:sx)
  exe "source " . fnameescape(s:sx)
endif
let &so = s:so_save | let &siso = s:siso_save
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
