package com.sun.training.ut.exercise_ten

import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise_ten.R
import com.sun.training.ut.exercise_ten.ui.ExerciseTenViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseTenViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTenViewModel

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockResources: Resources

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(mockContext.resources).thenReturn(mockResources)
        Mockito.`when`(mockResources.getString(R.string.ex_10_user_name_default))
            .thenReturn(USER_NAME)
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_black)).thenReturn(BLACK)
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_gold)).thenReturn(GOLD)
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_silver)).thenReturn(SILVER)
        Mockito.`when`(mockResources.getString(R.string.ex_10_class_type_unknown))
            .thenReturn(NO_MEMBER)
        viewModel = ExerciseTenViewModel(mockResources)
    }

    @Test
    fun validateTotal_withSilver() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = "1000.0"
        viewModel.printInvoice()
        Assert.assertEquals(1000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(1000.0, viewModel.invoice.value?.total)
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withSilver_with3000() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = "3000.0"
        viewModel.printInvoice()
        Assert.assertEquals(3000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(30.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(2970.0, viewModel.invoice.value?.total)
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withSilver_with5000() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = "5000.0"
        viewModel.printInvoice()
        Assert.assertEquals(5000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(100.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(4900.0, viewModel.invoice.value?.total)
        Assert.assertEquals(true, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withSilver_with10000() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = "10000.0"
        viewModel.printInvoice()
        Assert.assertEquals(10000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(400.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(9600.0, viewModel.invoice.value?.total)
        Assert.assertEquals(true, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withNoMember() {
        viewModel.updateMemberClassType(NO_MEMBER)
        viewModel.subTotal.value = "1000.0"
        viewModel.printInvoice()
        Assert.assertEquals(1000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(1000.0, viewModel.invoice.value?.total)
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withNoMember_with3000() {
        viewModel.updateMemberClassType(NO_MEMBER)
        viewModel.subTotal.value = "3000.0"
        viewModel.printInvoice()
        Assert.assertEquals(3000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(3000.0, viewModel.invoice.value?.total)
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withNoMember_with5000() {
        viewModel.updateMemberClassType(NO_MEMBER)
        viewModel.subTotal.value = "5000.0"
        viewModel.printInvoice()
        Assert.assertEquals(5000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(5000.0, viewModel.invoice.value?.total)
        Assert.assertEquals(true, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withNoMember_with10000() {
        viewModel.updateMemberClassType(NO_MEMBER)
        viewModel.subTotal.value = "10000.0"
        viewModel.printInvoice()
        Assert.assertEquals(10000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(10000.0, viewModel.invoice.value?.total)
        Assert.assertEquals(true, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withGold() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = "1000.0"
        viewModel.printInvoice()
        Assert.assertEquals(1000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(1000.0, viewModel.invoice.value?.total)
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withGold_with3000() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = "3000.0"
        viewModel.printInvoice()
        Assert.assertEquals(3000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(90.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(2910.0, viewModel.invoice.value?.total)
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withGold_with5000() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = "5000.0"
        viewModel.printInvoice()
        Assert.assertEquals(5000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(250.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(4750.0, viewModel.invoice.value?.total)
        Assert.assertEquals(true, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withGold_with10000() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = "10000.0"
        viewModel.printInvoice()
        Assert.assertEquals(10000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(1000.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(9000.0, viewModel.invoice.value?.total)
        Assert.assertEquals(true, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withBlack() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = "1000.0"
        viewModel.printInvoice()
        Assert.assertEquals(1000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(1000.0, viewModel.invoice.value?.total)
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withBlack_with3000() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = "3000.0"
        viewModel.printInvoice()
        Assert.assertEquals(3000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(150.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(2850.0, viewModel.invoice.value?.total)
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withBlack_with5000() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = "5000.0"
        viewModel.printInvoice()
        Assert.assertEquals(5000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(350, viewModel.invoice.value?.discount?.toInt())
        Assert.assertEquals(4650.0, viewModel.invoice.value?.total)
        Assert.assertEquals(true, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withBlack_with10000() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = "10000.0"
        viewModel.printInvoice()
        Assert.assertEquals(10000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(1500.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(8500.0, viewModel.invoice.value?.total)
        Assert.assertEquals(true, viewModel.invoice.value?.giftAccepted)
    }

    @Test
    fun validateTotal_withSilver_From0_To2999() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = "2900.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(2900.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(2900.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withSilver_From3000_To4999() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = "4000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(4000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(40.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(3960.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withSilver_From5000_To9999() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = "7000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(7000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(140.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(6860.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withSilver_From10000() {
        viewModel.updateMemberClassType(SILVER)
        viewModel.subTotal.value = "11000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(11000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(440.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(10560.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withGold_From0_To2999() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = "2900.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(2900.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(2900.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withGold_From3000_To4999() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = "4000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(4000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(120.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(3880.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withGold_From5000_To9999() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = "7000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(7000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(350.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(6650.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal__withGold_From10000() {
        viewModel.updateMemberClassType(GOLD)
        viewModel.subTotal.value = "11000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(11000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(1100.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(9900.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withBlack_From0_To2999() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = "2900.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(2900.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(2900.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withBlack_From3000_To4999() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = "4000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(4000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(200.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(3800.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withBlack_From5000_To9999() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = "7000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(7000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(490, viewModel.invoice.value?.discount?.toInt())
        Assert.assertEquals(6510.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal__withBlack_From10000() {
        viewModel.updateMemberClassType(BLACK)
        viewModel.subTotal.value = "11000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(11000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(1650.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(9350.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withNoMember_From0_To2999() {
        viewModel.updateMemberClassType(NO_MEMBER)
        viewModel.subTotal.value = "2900.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(2900.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(2900.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withNoMember_From3000_To4999() {
        viewModel.updateMemberClassType(NO_MEMBER)
        viewModel.subTotal.value = "4000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(4000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(4000.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal_withNoMember_From5000_To9999() {
        viewModel.updateMemberClassType(NO_MEMBER)
        viewModel.subTotal.value = "7000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(7000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0, viewModel.invoice.value?.discount?.toInt())
        Assert.assertEquals(7000.0, viewModel.invoice.value?.total)
    }

    @Test
    fun validateTotal__withNoMember_From10000() {
        viewModel.updateMemberClassType(NO_MEMBER)
        viewModel.subTotal.value = "11000.0"
        viewModel.printInvoice()
        Assert.assertEquals(false, viewModel.invoice.value?.giftAccepted)
        Assert.assertEquals(11000.0, viewModel.invoice.value?.subTotal)
        Assert.assertEquals(0.0, viewModel.invoice.value?.discount)
        Assert.assertEquals(11000.0, viewModel.invoice.value?.total)
    }

    companion object {
        private const val SILVER = "Hạng Bạc"
        private const val GOLD = "Hạng Vàng"
        private const val BLACK = "Hạng Đen"
        private const val USER_NAME = "Bach Ngoc Hoai"
        private const val NO_MEMBER = "Không phải hội viên"
    }
}
