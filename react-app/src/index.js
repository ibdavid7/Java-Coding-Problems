import React from "react";
import ReactDOM from "react-dom";
import Teacher, {promote as pr} from "./teacher";
import {Person} from "./person";

const element = <h1>Hello World</h1>;

console.log(element);
ReactDOM.render(element, document.getElementById('root'));

function sayHello() {
    for (var i = 0; i < 5; i++) {
        console.log(i);
    }
    console.log(i);
}

sayHello();

const person = {
    name: 'Mosh',
    walk: function () {
        console.log('Walking');
    },
    talk() {
        console.log('Talking')
    }
};

const targetMember = 'name';
person.talk();
person[targetMember] = 'Josh';
person["walk"]();

const dogWalker = {
    walk() {
        console.log('dog walker walking');
        console.log(this);
        return 1;
    }
}

dogWalker.walk(); // function executed as an object method so this will return ref to the object;

const walkStandAlone = dogWalker.walk.bind(dogWalker);          // in JS function is an object so dogWalker.walk is an
// object with
// object methods available

walkStandAlone(); // function executed as stand alone against global context so this return ref to global context;

console.log(walkStandAlone);

const square = numb => numb * numb;

console.log(square(5));

const jobs = [
    {id: 1, isActive: true},
    {id: 2, isActive: true},
    {id: 3, isActive: false},
];

const activeJobs = jobs.filter(job => job.isActive);

console.log(activeJobs);

const car = {
    drive() {
        // const self = this;
        setTimeout(() => {
            console.log('this: ', this);
        }, 1000);
    },
};

car.drive();

const colors = ['green', 'blue', 'yellow'];

const items = colors.map(item => `<li>${item}</li>`);

console.log(items);

const address = {
    street: 'a',
    city: 'b',
    country: 'c',
}

const {street: st, city, country} = address;

console.log(st, city, country);

const f = [1,2,3];
const s = [4,5,6];

const comb1 = [...f, 'a', ...s, 'b'];

console.log(comb1);

const  dupl = [...f,'c'];
dupl[6] = 7;
dupl.push(9, 4, 11);
console.log(dupl);

const o1 = {name: 'Mosh'};
const o2 = {job: 'Instructor'};
const combObj = {...o1, ...o2, locaion: 'Australia'};
console.log(combObj);
const clo1 = {...o1};
console.log(clo1);



const dave = new Person('Dave');

console.log(dave);

const daveTeacher = new Teacher('dave', 'BA Math');
daveTeacher.teach();