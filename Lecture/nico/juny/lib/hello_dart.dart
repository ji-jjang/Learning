main() {
  Employee employee = Employee()
    ..name = 'Kim'
    ..setAge(25)
    ..showInfo();

  employee.name = 'Park';
  employee.setAge(30);
  employee.showInfo();
}

class Employee {
  var name = 'employee';
  int age = 0;

  setAge(int age) {
    this.age = age;
  }

  showInfo() {
    print('$name is $age');
  }
}
