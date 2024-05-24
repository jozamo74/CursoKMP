package previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import data.ExpenseManager
import model.Expense
import model.ExpenseCategory
import presentation.ExpensesUiState
import ui.AllExpensesHeader
import ui.ExpensesItem
import ui.ExpensesScreen
import ui.ExpensesTotalHeader

@Preview(showBackground = true)
@Composable
fun ExpensesTotalPreview() {
    ExpensesTotalHeader(total = 1028.8)
}

@Preview(showBackground = true)
@Composable
fun AllExpensesHeaderPreview() {
    AllExpensesHeader()
}

@Preview(showBackground = true)
@Composable
fun ExpensesItemPreview() {
    ExpensesItem(expense = ExpenseManager.fakeExpenseList[0], onExpenseClick = {})
}

@Preview(showBackground = true)
@Composable
fun ExpenseScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState(
            expenses = ExpenseManager.fakeExpenseList,
            total = 1052.2
        ), onExpenseClick = {})
}