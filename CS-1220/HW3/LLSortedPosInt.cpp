// Author:  Christopher LaFave
// Date:    3/5/22
// Purpose: To implement a simple, sorted linked-list of positive integers

#include "LLSortedPosInt.h"

// The linked-list is constructed of Node elements
struct Node {
    int   key;
    Node* next;
};

// the following function is not a member function, it is a convenience
// function which exists to make the implementation of the LLSortedPosInt
// class more concise

// createNode() allocates a Node and initializes it with the
// given parameter values to simplify the initilization of a Node
static NodePtr createNode(int key, NodePtr p) {
    // allocate a new Node for storing the given key value
    NodePtr n = new Node;

    // store the key value and the next pointer
    n->key = key;
    n->next = p;

    // return the new Node to the caller
    return n;
}

// Student implementation of LLSortedPosInt begins here
// Constructors
LLSortedPosInt::LLSortedPosInt() {
    head = createNode(HEAD_OF_LIST, NULL);
}

LLSortedPosInt::LLSortedPosInt(int key) {
    // create the sentinal Node at the head of the list
    head = createNode(HEAD_OF_LIST, NULL);

    // add the single element key, as long as it is positive
    if (key > 0) {
        insert(key);
    }
}

LLSortedPosInt::LLSortedPosInt(int* keys, int n) {
    // create the sentinal node at the head of the list
    head = createNode(HEAD_OF_LIST, NULL);

    for (int i = 0; i < n; i++) {
        if (keys[i] > 0) {
            insert(keys[i]);
        }
    }
}

LLSortedPosInt::LLSortedPosInt(const LLSortedPosInt& l) {
    head = createNode(l.head->key, NULL);
    for (NodePtr p = l.head->next; p != NULL; p = p->next){
        insert(p->key);
    }
}

// Destructor
LLSortedPosInt::~LLSortedPosInt() {
    for (NodePtr p = this->head; this->head != NULL; p = head) {
        head = head->next;
        delete p;
    }
}

// Assignment Operator
LLSortedPosInt& LLSortedPosInt::operator=
(const LLSortedPosInt& l) {
    // handle self assignment
    if (this == &l) {
        return *this;
    }

    //swaps temp and this lists, old *this gets destroyed (as temp) when it goes out of scope
    LLSortedPosInt temp(l);
    swap(temp.head, this->head);
    return *this;
}

// Print Operator (a non-member function)
ostream& operator<<  (ostream& out, const LLSortedPosInt& l) {
    
    // an empty list will be printed as <>
    if (l.isEmpty()) {
        out << "<>";
        return out;
    }
    // a singleton list (a list having one key value k) will be
    //     printed as <k>
    else if (l.head->next->next == NULL) {
        out << "<" << l.head->next->key << ">";
    }
    // a list having multiple keys such as 2, 5, 7 will be printed
    //     as <2, 5, 7>
    else {
        out << "<";

        for (NodePtr p = l.head->next; p != NULL; p = p->next) {
            out << p->key;
            if (p->next != NULL) {
                out << ", ";
            }
        }
        out << ">";
    }
    
    return out;
}

// Boolean Functions
bool LLSortedPosInt::isEmpty() const {
    // return true if only the sentinal is in the list; return false otherwise
    if (!this->head->next) {
        return true;
    }
    return false;
}

bool LLSortedPosInt::containsElement(int key) const {
    // return true if key is in the list; return false otherwise
    NodePtr p = this->head->next;
    while (p!=NULL) {
        if (p->key == key) { 
            return true; 
        }
        else {
            p = p->next;
        }
    }
    return false;
}

// Other Operator Member Functions
bool LLSortedPosInt::operator==(const LLSortedPosInt& l) const {
    // compare the Nodes in *this with the Nodes in l
    // if all Node key values in *this match the cooresponding
    //  Node key values in l, then the lists are equivalent

    NodePtr thisPtr = this->head;
    NodePtr rhsPtr = l.head;

    while (thisPtr != NULL && rhsPtr != NULL) {
        if (thisPtr->key != rhsPtr->key) {
            return false;
        }
        thisPtr = thisPtr->next;
        rhsPtr = rhsPtr->next;
    }

    if (thisPtr != NULL || rhsPtr != NULL) {
        return false;
    }

    return true;
}

bool LLSortedPosInt::operator!=(const LLSortedPosInt& l) const {
    // do the opposite of operator==
    if ((*this) == l) {
        return false;
    }
    else {
        return true;
    }
}

// Other Operator Functions (non-member functions)
LLSortedPosInt  operator+ (const LLSortedPosInt& l1,
    const LLSortedPosInt& l2) {
    // create a copy of l1 and add each element of l2 to it in 
    // the correct (sorted ascending) order, allow duplicates

    LLSortedPosInt sum(l1);

    NodePtr l1Ptr = l1.head->next;
    NodePtr l2Ptr = l2.head->next;
    NodePtr sumPtr = sum.head->next;
    bool didInsert = false;

    while (l2Ptr != NULL) {
        //checks if we hit the end of list 1
        if (l1Ptr == NULL) {
            sum.insert(l2Ptr->key);
            didInsert = true;
        }
        //checks if the key should be inserted, and does.
        else if (l2Ptr->key <= l1Ptr->key) {
            sum.insert(l2Ptr->key);
            didInsert = true;
        }

        //updates pointers
        if (l1Ptr != NULL) {
            l1Ptr = l1Ptr->next;
        }

        if (didInsert) {
            l2Ptr = l2Ptr->next;
        }
        
        if (sumPtr != NULL) {
            sumPtr = sumPtr->next;
        }
        didInsert = false;
    }
    return sum;
}

LLSortedPosInt  operator- (const LLSortedPosInt& l1,
    const LLSortedPosInt& l2) {
    // copy l1 and remove all of l2 from l1, taking care to 
    // reclaim any storage -- do not to remove the sentinal Node
    LLSortedPosInt diff(l1);
    NodePtr l1Ptr = l1.head->next;
    NodePtr l2Ptr = l2.head->next;
    NodePtr diffPtr = diff.head->next;
    bool didDelete = false;

    while (l1Ptr != NULL && l2Ptr != NULL) {
        //checks if the keys are the same, and removes if so
        if (l2Ptr->key == l1Ptr->key) {
            diff.remove(l2Ptr->key);
            didDelete = true;
        }

        //updates pointers
            l1Ptr = l1Ptr->next;

        if (didDelete) {
            l2Ptr = l2Ptr->next;
        }

        if (diffPtr != NULL) {
            diffPtr = diffPtr->next;
        }
        didDelete = false;
    }
    return diff;
}

// The following helper functions are provide to assist you in
// building the class--these helper functions are useful in
// several places among the functions you will write--take time
// to learn what they do

// insert() inserts an element in the linked list in sorted order
void LLSortedPosInt::insert(int key) {

    // setup pointers to walk the list
    NodePtr npp = head;
    NodePtr npc = head->next;

    // walk the list, searching until the given key value is exceeded
    while (npc != NULL && npc->key <= key) {
        npp = npc;
        npc = npc->next;
    }

    // insert the new value into the list
    npp->next = createNode(key, npc);
}

// remove() removes an element from the list (if it is present)
void LLSortedPosInt::remove(int key) {

    // negative values should not be stored in the list
    if (key <= 0) {
        return;
    }

    // setup pointers to walk the list
    NodePtr npp = head;
    NodePtr npc = head->next;

    // search the list until the end (if necessary)
    while (npc != NULL) {

        // if the key value is found, then splice this Node from the list and
        // reclaim its storage
        if (npc->key == key) {
            npp->next = npc->next;
            delete npc;
            break;
        }

        // walk the pointers to the next Node
        npp = npc;
        npc = npc->next;
    }
}