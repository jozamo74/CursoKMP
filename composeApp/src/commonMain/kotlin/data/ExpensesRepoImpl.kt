package data

import com.expenseApp.db.AppDatabase
import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpensesRepoImpl(
    private val expenseManager: ExpenseManager,
    private val appDatabase: AppDatabase
): ExpenseRepository {

    private val queries = appDatabase.expensesDbQueries
    override fun getAllExpenses(): List<Expense> {
        queries.selectAll()
        return expenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        expenseManager.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        expenseManager.editExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expenseManager.getCategories()
    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        return expenseManager.deleteExpense(expense)
    }


}