import React from "react";

import NewExpense from "./components/NewExpense/NewExpense";
import Expenses from "./components/Expenses/Expenses";
import { useState } from "react/cjs/react.development";


  const DUMMY_EXPENSES = [
    { id:'e1', title: "Base Ball", amount: 150, date: new Date(2019, 4, 16) },
    { id:'e2', title: "New TV", amount: 899.49, date: new Date(2021, 3, 15) },
    { id:'e3', title: "Car Insurance", amount: 294.67, date: new Date(2021, 2, 28) },
    { id:'e4', title: "New Desk(Wooden)", amount: 500, date: new Date(2022, 6, 10) },
  ];

  function App() {
    const [expenses, setExpenses] = useState(DUMMY_EXPENSES);

  const addExpensehandler = (expense) => {
   // console.log("In App.js");
    //console.log(expense);
    setExpenses((prevExpenses) => {
      return [expense, ...prevExpenses];
    });
  };

  // return React.createElement(
  //   "div",
  //   {},
  //   React.createElement("h2", {}, "Let's get started!"),
  //   React.createElement(Expenses, { items: expenses })
  // );

  return (
    <div>
      <NewExpense onAddExpense={addExpensehandler} />
      <Expenses items={expenses} />
    </div>
  );
}

export default App;
