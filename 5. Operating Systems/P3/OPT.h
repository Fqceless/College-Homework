#ifndef _OPT_H
#define _OPT_H

#include <iostream>
#include <list>
#include <vector>
#include "ReplacementAlgorithm.h"


class OPT : public ReplacementAlgorithm {
public:
    //added the vector of the file in the constructor for fortune-telling
    OPT(int numPageFrames, std::vector<int> stringStream);
    void insert(int pageNumber) override;

private:
    // data structure to store the int page frame list
    //<data type> frameList;
    std::list<int> frameList;
    std::vector<int> refrenceStringStream; //added this
    int counter; //added this, keeps track of the vector's index
};

#endif