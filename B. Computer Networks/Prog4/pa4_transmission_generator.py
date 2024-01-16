
C1 = [1,1,-1,1]
C2 = [-1,1,-1,-1]

M1 = "H"#ELLO"
M2 = "O"#YEAH"

ENCODING_MAP = {"A" : [1,1,1,1,1], \
                "B" : [1,1,1,1,-1], \
                "C" : [1,1,1,-1,1], \
                "D" : [1,1,1,-1,-1], \
                "E" : [1,1,-1,1,1], \
                "F" : [1,1,-1,1,-1], \
                "G" : [1,1,-1,-1,1], \
                "H" : [1,1,-1,-1,-1], \
                "I" : [1,-1,1,1,1], \
                "J" : [1,-1,1,1,-1], \
                "K" : [1,-1,1,-1,1], \
                "L" : [1,-1,1,-1,-1], \
                "M" : [1,-1,-1,1,1], \
                "N" : [1,-1,-1,1,-1], \
                "O" : [1,-1,-1,-1,1], \
                "P" : [1,-1,-1,-1,-1], \
                "Q" : [-1,1,1,1,1], \
                "R" : [-1,1,1,1,-1], \
                "S" : [-1,1,1,-1,1], \
                "T" : [-1,1,1,-1,-1], \
                "U" : [-1,1,-1,1,1], \
                "V" : [-1,1,-1,1,-1], \
                "W" : [-1,1,-1,-1,1], \
                "X" : [-1,1,-1,-1,-1], \
                "Y" : [-1,-1,1,1,1], \
                "Z" : [-1,-1,1,1,-1], \
                " " : [-1,-1,1,-1,1], \
                "," : [-1,-1,1,-1,-1], \
                "." : [-1,-1,-1,1,1], \
                "'" : [-1,-1,-1,1,-1], \
                "?" : [-1,-1,-1,-1,1], \
                "!" : [-1,-1,-1,-1,-1]}

def encodeStringToCDMA(stringInput, cdmaCode) :
    stringEncodedCDMA = []
    for letter in stringInput :
        letterEncoding = ENCODING_MAP[letter]
        letterEncodedSignal = []
        for letterBit in letterEncoding :
            letterEncodedCDMASignal = []
            for cdmaBit in cdmaCode :
                letterEncodedCDMASignal.append(letterBit*cdmaBit)
            letterEncodedSignal.append(letterEncodedCDMASignal)
        stringEncodedCDMA.append(letterEncodedSignal)
    return stringEncodedCDMA

def addTransmissions(t1, t2) :
    # this simple method only works for same length strings
    if(len(t1)!=len(t2)) : return 0
    sumOfTransmissions = []
    for letter in range(len(t1)) :
        letterEncodedSignal = []
        for letterBit in range(len(t1[letter])) :
            letterEncodedCDMASignal = []
            for cdmaBit in range(len(t1[letter][letterBit])) :
                letterEncodedCDMASignal.append(t1[letter][letterBit][cdmaBit] + t2[letter][letterBit][cdmaBit])
            letterEncodedSignal.append(letterEncodedCDMASignal)
        sumOfTransmissions.append(letterEncodedSignal)
    return sumOfTransmissions

def outputRawTransmission(t, f) :
    output = ""
    for letter in range(len(t)) :
        for letterBit in range(len(t[letter])) :
            for cdmaBit in range(len(t[letter][letterBit])) :
                output += str(t[letter][letterBit][cdmaBit]) + ","
    # remove the trailing comma
    print(output[:-1])
    f.write(output[:-1])
     
trans1 = encodeStringToCDMA(M1,C1)
trans2 = encodeStringToCDMA(M2,C2)

combinedTrans = addTransmissions(trans1, trans2)
      
outfile = open('transmission.txt', 'w')
outputRawTransmission(combinedTrans, outfile)
outfile.close()

