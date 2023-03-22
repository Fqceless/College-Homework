#ifndef _LRU_H
#define _LRU_H

#include <iostream>
#include <list>
#include "ReplacementAlgorithm.h"


class LRU : public ReplacementAlgorithm {
public:
    LRU(int numPageFrames);
    void insert(int pageNumber) override;

private:
    // data structure to store the int page frame list
    //<data type> frameList;
    std::list<int> frameList;
};

#endif