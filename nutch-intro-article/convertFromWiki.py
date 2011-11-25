import re

# This is very crude - you need to do a lot of manual processing after running this!

wikiText = open('wiki.txt').read()

wikiText = re.sub(r'\n\r', r'\n', wikiText)
wikiText = re.sub(r'\r\n', r'\n', wikiText)

wikiText = re.sub(r'\[\[TableOfContents\]\]', r'', wikiText)

wikiText = re.sub(r'(?m)^= (.+?) =$', r'<h3>\1</h3>', wikiText)
wikiText = re.sub(r'(?m)^== (.+?) ==$', r'<h4>\1</h4>', wikiText)
wikiText = re.sub(r'(?m)^=== (.+?) ===$', r'<h5>\1</h5>', wikiText)

wikiText = re.sub(r"'''''(.+?)'''''", r'<b><i>\1</i></b>', wikiText)
wikiText = re.sub(r"'''(.+?)'''", r'<b>\1</b>', wikiText)
wikiText = re.sub(r"''(.+?)''", r'<i>\1</i>', wikiText)

wikiText = re.sub(r"`(.+?)`", r'<code>\1</code>', wikiText)

wikiText = re.sub(r"{{{", r'<pre>', wikiText)
wikiText = re.sub(r"}}}", r'</pre>', wikiText)

wikiText = re.sub(r'(?m)^ \* (.+?)$', r'<li>\1</li>', wikiText)
#wikiText = re.sub(r'<li>(.+?)</li>', r'<ul>\n<li>bloop</li>\n</ul>', wikiText)
wikiText = re.sub(r'\n\n<li>(.+?)</li>\n\n', r'<ul>\n<li>\1</li>\n</ul>', wikiText)

wikiText = re.sub(r'(?m)^ 1.(#\d)? (.+?)$', r'<li>\2</li>', wikiText)
wikiText = re.sub(r'\n\n<li>(.+?)</li>\n\n', r'<ol>\n<li>\1</li>\n</ol>', wikiText)

#wikiText = re.sub(r'>\n', r'>\n<p>', wikiText)
#wikiText = re.sub(r'>\n\n', r'>\n<p>', wikiText)
#wikiText = re.sub(r'\n\n(.+?)\n\n', r'\n\n</p>\1<p>\n\n', wikiText)

print """<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

  <head>
    <title>Article Title</title>
  </head>

  <body>

    <h2>Article Title</h2>
    <p>
    by Tom White
    </p>
""" + wikiText + """
  </body>
</html>
"""
