#ifndef _FIFO_H
#define _FIFO_H

#include <iostream>
#include <list>
#include "ReplacementAlgorithm.h"

class FIFO : public ReplacementAlgorithm {
public:
    FIFO(int numPageFrames);
    void insert(int pageNumber) override;

private:
    // data structure to store the int page frame list
    //<data type> frameList;
    std::list<int> frameList;
};

#endif