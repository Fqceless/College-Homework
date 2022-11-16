%Christopher LaFave, Section #2

%Question #1
%Write Prolog clauses to express the following three relationships, given the parent/2 relationship: 
%grandparent/2, sibling/2, cousin/2.
grandparent(X,Y) :- parent(X,Z), parent(Z,Y).
sibling(X,Y) :- parent(Z,X), parent(Z,Y), X \= Y.
cousin(X,Y) :- grandparent(Z,X), grandparent(Z,Y), X \= Y, not(sibling(X,Y)).

parent(jill,amy).
parent(jill,bob).
parent(amy,grace).
parent(amy,joe).
parent(bob,james).

%Question #2
%define a recursive predicate in/2, that tells us which doll is 
%(directly or indirectly) contained in which other dolls. 
directlyIn(katerina, olga).
directlyIn(olga, natasha).
directlyIn(natasha, irina).

in(X,Y) :- directlyIn(X,Y).
in(X,Y) :- directlyIn(X,Z), in(Z,Y).

%Question #3
%Write a recursive predicate travelFromTo/2 that tells us 
%when we can travel by train between two towns.
directTrain(baltimore,washington).
directTrain(wilmington,baltimore).
directTrain(philadelphia,wilmington).
directTrain(newark,philadelphia).
directTrain(newyorkcity,newark).
directTrain(newhaven,newyorkcity).
directTrain(providence,newhaven).
directTrain(boston,providence).

travelFromTo(X,Y) :- directTrain(X,Y).
travelFromTo(X,Y) :- directTrain(X,Z), travelFromTo(Z,Y).
%I'm not sure if the travel goes both ways, if it does then include this rule
%travelFromTo(X,Y) :- travelFromTo(Y,X).

%Question #4
%Write a predicate travel/2 which determines whether it is possible to travel 
%from one place to another by chaining together car, train, and plane journeys.
byCar(auckland,hamilton).
byCar(hamilton,raglan).
byCar(valmont,saarbruecken).
byCar(valmont,metz).

byTrain(metz,frankfurt).
byTrain(saarbruecken,frankfurt).
byTrain(metz,paris).
byTrain(saarbruecken,paris).

byPlane(frankfurt,bangkok).
byPlane(frankfurt,singapore).
byPlane(paris,losAngeles).
byPlane(bangkok,auckland).
byPlane(singapore,auckland).
byPlane(losAngeles,auckland).

travel(X,Y) :- byCar(X,Y).
travel(X,Y) :- byTrain(X,Y).
travel(X,Y) :- byPlane(X,Y).
travel(X,Y) :- byCar(X,Z), travel(Z,Y).
travel(X,Y) :- byTrain(X,Z), travel(Z,Y).
travel(X,Y) :- byPlane(X,Z), travel(Z,Y).
%I'm not sure if the travel goes both ways, if it does then include this rule
%travel(X,Y) :- travel(Y,X).

%Question #5
%Write a predicate listtran(L,E) which translates a list of Latin number 
%words to the corresponding list of English number words.
tran(unus,one).
tran(duo,two).
tran(tres,three).
tran(quattuor,four).
tran(quinque,five).
tran(sex,six).
tran(septem,seven).
tran(octo,eight).
tran(novem,nine).

listtran([],[]).
listtran([Lh|Lt],[Eh|Et]) :- tran(Lh,Eh), listtran(Lt,Et).

%Question #6
%Write a Prolog predicate move/3 - move(State,Move,NextState) where State is the starting position, 
%Move is one of the four basic moves, and NextState is the result of applying that move to State.
%move([w,w,w,w],wolf,[e,e,w,w]).
change(e,w).
change(w,e).
move([X,X,Goat,Veg],wolf,[Y,Y,Goat,Veg]) :- change(X,Y).
move([X,Wolf,X,Veg],goat,[Y,Wolf,Y,Veg]) :- change(X,Y).
move([X,Wolf,Goat,X],veggie,[Y,Wolf,Goat,Y]) :- change(X,Y).
move([X,Wolf,Goat,Veg],nothing,[Y,Wolf,Goat,Veg]) :- change(X,Y).

oneEq(X,X,_).
oneEq(X,_,X).
safe([Man,Wolf,Goat,Veg]) :- oneEq(Man,Goat,Wolf), oneEq(Man,Veg,Goat).

solution([e,e,e,e],_).
solution(State,[Move|OtherMoves]) :- 
    move(State,Move,NextState), 
    safe(NextState), 
    solution(NextState,OtherMoves).