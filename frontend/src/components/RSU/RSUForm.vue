<template>
<el-dialog title="Add new RSU" :visible.sync="isAddFormVisible" width="50%" center>
  <el-row :gutter="200">
    <el-col :span="10">
      <el-form :model="newRSU" label-width="110px" size="mini">
        <el-form-item label="Settlement Day">
          <el-date-picker
            v-model="newRSU.settleDate"
            type="date"
            placeholder="Pick a day">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="Count">
          <el-input-number v-model="newRSU.count" controls-position="right"></el-input-number>
        </el-form-item>
        <el-form-item label="Value">
          <el-input-number
            :precision="2"
            :step="1.0"
            :min="0.0"
            v-model="newRSU.value"
            controls-position="right"></el-input-number>
        </el-form-item>
        <el-form-item label="USD to HUF">
          <el-input-number
            :precision="2"
            :step="1.0"
            :min="0.0"
            v-model="newRSU.usdhuf"
            controls-position="right"></el-input-number>
        </el-form-item>
        <el-form-item>
          <el-checkbox v-model="newRSU.isTaxPaid">Tax Paid</el-checkbox>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="14">
      <h4> Earning: {{ newRSU.count * newRSU.value * newRSU.usdhuf | currencyFormatter }} Ft</h4>
      <h4> Tax Base ( #{{ newRSU.count }} X ${{ newRSU.value }} X {{ newRSU.usdhuf }} X {{ this.taxBase }}% ) = {{ this.exactTaxBase | currencyFormatter }} Ft</h4>
      <h4> Taxes: </h4>
      <el-table show-summary :summary-method="getSummaries" :data="newTaxes">
        <el-table-column prop="name" label="Type"></el-table-column>
        <el-table-column label="Percentage">
          <template slot-scope="scope">
            {{ scope.row.taxType.percentage | percentFormatter }}
          </template>
        </el-table-column>
        <el-table-column label="Value">
          <template slot-scope="scope">
            {{ scope.row.value | currencyFormatter }} Ft
          </template>
        </el-table-column>
      </el-table>
    </el-col>
  </el-row>
  <span slot="footer" class="dialog-footer">
    <el-button @click="closeForm">Cancel</el-button>
    <el-button type="primary" @click="submitForm">Confirm</el-button>
  </span>
</el-dialog>
</template>

<script>
import repo from '../../repositories/RSURepository'
import taxRepo from '../../repositories/TaxRepository'

export default {
  name: 'RSUForm',
  computed: {
    taxBase () {
      return this.$store.getters['TaxBaseStore/getTaxBase']
    },
    taxTypes () {
      return this.$store.getters['TaxTypeStore/getTaxTypes']
    },
    exactTaxBase () {
      return this.newRSU.count * this.newRSU.value * this.newRSU.usdhuf * (this.taxBase / 100.0)
    },
    isAddFormVisible () {
      return this.$store.getters['RSUStore/isAddFormVisible']
    },
    newRSU () {
      return this.$store.getters['RSUStore/getNewRSU']
    },
    newTaxes () {
      const taxes = []
      this.taxTypes.forEach(taxType => {
        taxes.push({
          'name': taxType.name,
          'taxType': taxType,
          'value': this.countTaxType(taxType.percentage),
          'dueDate': this.newRSU.settleDate
        })
      })
      return taxes
    }
  },
  methods: {
    countTaxType (val) {
      return this.exactTaxBase * val / 100.0
    },
    async submitForm () {
      try {
        const updated = this.newRSU.id ? await repo.update(this.newRSU) : await repo.save(this.newRSU)
        if (this.newRSU.id && this.newRSU.id >= 0) {
          this.$store.commit('RSUStore/updateRsu', updated)
          this.saveNewTaxes(updated.id, true)
        } else {
          this.$store.commit('RSUStore/addRsu', updated)
          this.saveNewTaxes(updated.id, false)
        }
      } catch (error) {
        this.$message.warning(error.message)
      }
      this.closeForm()
    },
    async saveNewTaxes (rsuId, isUpdate) {
      this.newTaxes.forEach(newTax => {
        newTax.rsu = rsuId
        const updated = isUpdate ? taxRepo.update(newTax) : taxRepo.save(newTax)
        if (isUpdate) {
          this.$store.commit('TaxStore/updateTax', updated)
        } else {
          this.$store.commit('TaxStore/addTax', updated)
        }
      })
    },
    closeForm () {
      this.$store.commit('RSUStore/clearNewRSU')
      this.$store.commit('RSUStore/setAddFormVisible', false)
    },
    getSummaries (param) {
      const { data } = param
      console.log(data)
      const sums = []
      sums[0] = 'Total'
      sums[1] = data.map(item => Number(item['taxType']['percentage'])).reduce((a, b) => a + b, 0)
      sums[1] = this.$options.filters.percentFormatter(sums[1])
      sums[2] = data.map(item => Number(item['value'])).reduce((a, b) => a + b, 0)
      sums[2] = this.$options.filters.currencyFormatter(sums[2]) + ' Ft'
      return sums
    }
  }
}
</script>

<style scoped>

</style>
