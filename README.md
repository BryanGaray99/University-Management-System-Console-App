# Globant University - BG 
Version 1.2.0 - Included a validation data input layer.

## Project Description

Globant University - BG is a university tracking project that combines the fundamentals of Object-Oriented Programming 
(OOP) and the Model-View-Controller (MVC) architecture. The project allows you to manage and track information about 
teachers, students, and classes in a fictional university, maintaining clean code and adhering to the four pillars 
of OOP: encapsulation, inheritance, polymorphism, and abstraction.

## Project Structure

The project is structured into the following folders:

<P align="center">
<img src="https://i.ibb.co/YprDXrS/project-Structure.png" alt="Preview.png" style="height: 400px;">
<P align="center">Project Structure</P>

### Design Pattern: MVC and SOLID Principles

The project is based on the Model-View-Controller (MVC) pattern to ensure an organized and modular structure. Here's 
how MVC is applied in the project:

- **Model**: Represents the data and business logic of the project. Models include classes like `Teacher`, `Student`, 
`Class`, `University`, and others.

- **View**: The view handles the presentation of data and user interaction. In this project, views are implemented 
through functions that display menus and options in the console.

- **Controller**: Controllers act as intermediaries between the model and the view. They manage the application's 
operations and logic. The five controllers in the project are: `ClassController`, `SchedulesController`, 
`StudentController`, `TeacherController`, and `UniversityController`.


### Use of SOLID Principles

- **Single Responsibility Principle (SRP)**: Each class and method adheres to a single responsibility. For example, the
`TeacherController` has separate methods for creating, modifying, and retrieving teacher information.

- **Open/Closed Principle (OCP)**: The code is designed to be open for extension and closed for modification. New 
classes can be added without altering existing code. For example, new types of teachers can be added without 
modifying the existing controller.

- **Liskov Substitution Principle (LSP)**: Derived classes can substitute base classes without changing the expected
behavior. The `FullTimeTeacher` and `PartTimeTeacher` classes are examples of this.

- **Interface Segregation Principle (ISP)**: Interfaces are kept small and specific to avoid implementing unnecessary 
methods. The `StudentClass` interface provides only relevant methods for student classes.

- **Dependency Inversion Principle (DIP)**: High-level modules do not depend on low-level modules. For example, By 
defining the `StudentClass` interface, we create an abstraction that allows high-level modules (e.g., controllers) 
to depend on it, rather than directly on the `Class` class.

## Models and Class Diagram

The project includes a variety of models representing key objects in the system, such as professors, students, classes, 
and schedules. These models use inheritance, encapsulation, polymorphism, and abstraction to maintain a consistent 
structure and leverage the four OOP pillars.

### Models:

- `Class`: Represents a class at the university. It encapsulates information about the class, such as its name, 
classroom, teacher, students, and schedule.

- `User`: Serves as the base class for users in the system. It includes common attributes like name, age, and a flag 
indicating if the user is active.

- `Employee` (Abstract Class): Extends the `User` class and represents an employee at the university. It includes an 
additional attribute, `employeeId`, to uniquely identify each employee.

- `Teacher` (Abstract Class): Extends the `Employee` class and represents a teacher. It defines methods and attributes 
common to all types of teachers.

- `FullTimeTeacher`: Extends the `Teacher` class and represents a full-time teacher. It includes attributes for 
experience years and base salary. The class implements the `calculateSalary` method to calculate the teacher's salary.

- `PartTimeTeacher`: Extends the `Teacher` class and represents a part-time teacher. It includes attributes for base
salary and active hours per week. The class also implements the `calculateSalary` method.

- `Student`: Extends the `User` class and represents a student at the university. Each student has a unique `studentId`.

- `StudentClass` (Interface): Defines methods for managing students within a class. It includes methods to add and 
remove students, get the teacher of the class, and retrieve a list of students.

- `WeeklySchedule`: Represents the weekly schedule of a class. It includes information about the days of the week 
and the class's start and end times.

- `University`: Represents the university as a whole. It includes lists of teachers, students, and classes, serving 
as the central data structure for the application.

These models adhere to object-oriented programming principles, such as encapsulation (by encapsulating attributes 
and providing access methods), inheritance (by extending base classes to create specialized classes), and abstraction 
(by defining interfaces and abstract classes). Polymorphism is also demonstrated through the use of abstract methods 
for calculating salaries in the `Teacher` subclasses.

### Class Diagram
<P align="center">
   <img src="https://i.ibb.co/6y9fZ7g/Diagrama-UMLv3.png" alt="Preview.png" style="height: 400px">
<P align="center">Link to the image: https://i.ibb.co/6y9fZ7g/Diagrama-UMLv3.png </P>
The diagram was make with the tool: Software Ideas Modeler. In the diagram we can see the classes and the relationships 
between them. The classes are represented by rectangles and the relationships by arrows. The arrows indicate the type of 
relation. 

- We can clearly see that Student and Employee `Inherit` from User, then Teacher `Inherit`  from Employee and 
FullTimeTeacher and PartTimeTeacher `Inherit`  from Teacher. 
- There is also a relationship of ``Aggregation`` between Class and WeeklySchedule, Teacher and Student because a class 
is made up of a schedule, a teacher and students.
- There is a relationship of ``Realization`` between StudentClass and Class, due to StudentClass is an interface 
that serves like the functionality that can use a Class.
- Finally, we have a ``Composition`` relationship between University and Classes, Teachers and Students. It is a 
composition because once the University is destroyed, the other classes are also destroyed.

## Controllers

The project includes five controllers that manage operations related to classes, schedules, students, teachers, and 
the university in general. Each controller adheres to SOLID principles and focuses on specific responsibilities.

### Controllers:

- `ClassController`: Manages class creation, status changes, and methods to get information about classes.
- `SchedulesController`: Controls class schedule management.
- `StudentController`: Manages student creation, status changes and methods to get information about students.
- `TeacherController`: Manages teacher creation, status changes and methods to get information about teachers.
- `UniversityController`: Coordinates operations that involve multiple controllers like classes and students.

## Views

The application features five views representing the main menus of the user interface. Each view corresponds to one 
of the following options:

1. Main Menu
   <br>
   <br>
   <P align="center">
   <img src="https://i.ibb.co/X3xfxKL/menu1.png" alt="Preview.png" style="height: 150px;">
   <br>
2. Modification Menu
   <br>
   <br>
   <P align="center">
   <img src="https://i.ibb.co/BZ9GyGn/menu2.png" alt="Preview.png" style="height: 150px;">
   <br>
3. Creation and Addition Menu
   <br>
   <br>
   <P align="center">
   <img src="https://i.ibb.co/vQgKSqZ/menu3.png" alt="Preview.png" style="height: 150px;">
   <br>
4. Change Status Menu
   <br>
   <br>
   <P align="center">
   <img src="https://i.ibb.co/vXd1kXW/menu4.png" alt="Preview.png" style="height: 150px;">
   <br>
5. Read Menu
   <br>
   <br>
   <P align="center">
   <img src="https://i.ibb.co/ZKp0vRR/menu5.png" alt="Preview.png" style="height: 150px;">

Each view displays options related to the operations that can be performed in that section.

## Utilities

The project includes utilities such as an identifier generator and a teacher salary calculator. These utilities 
are used in various controllers to simplify data management.

## Validation

The project includes a validation layer in which all user input is validated before being processed. This ensures
that the application does not crash due to invalid input.

## How to Run the Project

Follow these steps to run the project:

1. Clone the project from the repository: `git clone https://github.com/BryanGaray99/University-Globant-BG.git`

2. Open the main file containing the `main` function of the application.

3. Run the project. This will initialize the application and display the main menu.

4. Use the menus and options to create, modify, and query information about professors, students, and classes in the 
university.

Enjoy managing your university with this application!

**Note:** Ensure you have all the necessary dependencies installed before running the project.

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

## Author 
Bryan Enrique Garay Benavidez :telescope:

## Final note

Special thanks to Globant and to the instructors of the Bootcamp: Joel, Jazmin and Felipe for the support, the 
knowledge shared and the good vibes during the course :sparkles:.