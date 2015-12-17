words = ['EMAAE', 'HUMBA', 'LMNRE', 'AUDSR', 'RTSUN', 'WHAVP', 
         'TOEMH', 'KITVE', 'DGEUS', 'TEATO', 'SHOSO', 'YHNME', 'EKLAI']

text = ''.join(words)
text = input("TEXT: ")
l = len(ctext)
print('\ntotal chars', l)

key = '35142'
def exec(ctext,key):
    n = len(key)
    print('\nkey length', n)

    # here we do exactly the same as what you did to encode, except we just
    # count the letters.  so chars[x] is the number of characters in column x,
    # which starts at 0 and is added 1 or 2 on each line.
    chars = [0] * n
    remaining = l
    ichar = 0
    chunk = 0
    while remaining > 0:
        k = min(chunk+1, remaining)
        chars[ichar] += k
        remaining -= k
        ichar = (ichar + 1) % n  # this indexes the columns 0..n-1,0..n-1,...
        chunk = (chunk + 1) % 2  # this goes 0,1,0,1,... and we +1 above

    # now we have the number of characters in each column, so display that
    print('\nkey digit and number of characters')
    for i, digit in enumerate(key):
        print(digit, chars[i])

    # but the ordering in the encrypted data is different, so we re-order to
    # match that.  this uses a bit of a trick in python - if we order a list of
    # pairs (a,b) then is it ordered by the first thing in the pair (the key 
    # digit) here.  so we order by the key digit, but also re-arrange the
    # columns sizes.
    digitsandchars = [(digit, chars[i]) for i, digit in enumerate(key)]
    print('\nbefore sorting', digitsandchars)
    digitsandchars = sorted(digitsandchars)
    print('after sorting', digitsandchars)

    # now that we have the columns sizes in the right order we can cut up the
    # text into the columns
    columns = [''] * n
    for i in range(n):
        digit, nchars = digitsandchars[i]
        columns[i] = ctext[:nchars]
        ctext = ctext[nchars:]
        print('digit', digit, 'column', columns[i])

    # now switch the columns back to the order they were originally
    ordered = [columns[int(key[i])-1] for i in range(n)]
    print('\nordered columns', ordered)

    # and finally we can decode by doing the same process as before - we pick
    # off 1 or 2 characters from each column until we have nothing left.
    print('\ndecode')
    icolumn = 0
    remaining = l
    chunk = 0
    while remaining > 0:
        column = ordered[icolumn]
        k = min(chunk+1, remaining)
        print(column[:k], end='')  # print the first k characters
        remaining -= k
        ordered[icolumn] = column[k:]  # remove the printed characters
        icolumn = (icolumn + 1) % n
        chunk = (chunk + 1) % 2
    print()


#PERMUTATE
exec(text,"123456")

