// Author:  Keith A. Shomper
// Date:    Jan 7, 2020
// Purpose: Starter code for HW#2
// Modified by: Christopher LaFave

#include <cstdio>
#include <cstring>
#include <string>
#include "ComplexNumber.h"

ComplexNumber::ComplexNumber(float real, float imag) {
    r = real;
    i = imag;
}

ComplexNumber::ComplexNumber(const ComplexNumber& rhs) {
    r = rhs.r;
    i = rhs.i;
}

 ComplexNumber::ComplexNumber(const char* str) {
    r = 0.0;
    i = 0.0;
    char *endptr = nullptr;

    r = strtod(str, &endptr);

    if (*endptr == 'i') {
        //testing for no real part
        if (*(endptr - 1) == '\0') {
            //testing for str == "i"
            r = 0;
            i = 1;
        }
        else if (*(endptr - 1) == '-') {
            //testing for str == "-i"
            r = 0;
            i = -1;
        }
        else {
            i = r;
            r = 0.0;
        }
    }

    else {
        i = strtod(endptr, nullptr);
        if (*(endptr + 1) == 'i') {
            //testing for +/- i
            if (*endptr == '+') {
                i = 1;
            }
            else if (*endptr == '-') {
                i = -1;
            }
        }
    }
}

ComplexNumber& ComplexNumber::operator=(const ComplexNumber& rhs) {
    r = rhs.r;
    i = rhs.i;
    return *this;
}

bool ComplexNumber::operator==(const ComplexNumber& rhs) const {
    return (r == rhs.r && i == rhs.i);
}

ComplexNumber operator+ (const ComplexNumber& a, const ComplexNumber& b) {
    ComplexNumber result;

    result.r = a.r + b.r;
    result.i = a.i + b.i;

    return result;
}

ComplexNumber operator- (const ComplexNumber& a, const ComplexNumber& b) {
    ComplexNumber result;

    result.r = a.r - b.r;
    result.i = a.i - b.i;

    return result;
}

ComplexNumber ComplexNumber::operator*(const ComplexNumber& rhs) {
    ComplexNumber mult(0, 0);

    mult.r = (this->r * rhs.r) - (this->i * rhs.i);
    mult.i = (this->r * rhs.i) + (rhs.r * this->i);
    
    return mult;
}

ostream& operator<< (ostream& out, const ComplexNumber& b) {
    bool rPrinted = false;

    if (b.r != 0 || (b.r == 0 && b.i == 0)) {
        out << b.r;
        rPrinted = true;
    }

    if (b.i > 0) {
        if (rPrinted) {
            out << "+";
        }
        if (b.i != 1) {
            out << b.i;
        }
        out << "i";
    }
    else if (b.i < 0) {
        if (b.i == -1) {
            out << "-";
        }
        else {
            out << b.i;
        }
        out << "i";

    }

    return out;
}

istream& operator>> (istream& in, ComplexNumber& b) {
    string test;
    in >> test;
    
    b = ComplexNumber(test.c_str());
    
    return in;
}