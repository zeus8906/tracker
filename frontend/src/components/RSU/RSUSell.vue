<template>
  <el-dialog width="60%" center :visible="isRsuSellVisible" @close="close">
    <el-row :gutter="20">
      <el-form :model="transaction" size="small" label-position="right" label-width="120px">
        <el-form-item label="Sell Price">
          <el-input-number v-model="transaction.sellPrice" :min="0" :precision="2"></el-input-number>
        </el-form-item>
        <el-form-item label="USD HUF">
          <el-input-number v-model="transaction.usdhuf" :min="0" :precision="2"></el-input-number>
        </el-form-item>
        <el-form-item label="Transaction fee">
          <el-input-number v-model="transaction.fee" :min="0" :precision="2"></el-input-number> USD
        </el-form-item>
        <el-form-item label="Realized income">
          <el-input-number v-model="transaction.income" :min="0" :precision="0"></el-input-number> HUF
        </el-form-item>
        <el-form-item label="SellDate">
          <el-date-picker v-model="transaction.sellDate" type="date" placeholder="Pick a day"></el-date-picker>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row :gutter="20">
      <el-table :data="rsus" height="400" stripe border>
        <el-table-column label="Id" prop="id" min-width="20"></el-table-column>
        <el-table-column label="RSU">
          <template slot-scope="scope">
            Settle date: {{ scope.row.settleDate | dateFormatter }} <br>
            #{{ scope.row.count }} X ${{ scope.row.value }} = $ {{ scope.row.count * scope.row.value | currencyFormatter}}
          </template>
        </el-table-column>
        <el-table-column label="To Sell">
          <template slot-scope="scope">
            <el-slider :min="0" :max="scope.row.count" :step="1" show-input v-model="transaction.items[scope.row.id]"> </el-slider>
          </template>
        </el-table-column>
        <el-table-column min-width="50">
          <template slot-scope="scope">
            <div v-if="scope.row.value<=transaction.sellPrice">
              <el-tag type="success">+{{ Math.round((transaction.sellPrice - scope.row.value)*100)/100 }}</el-tag>
              <p v-for="type in taxesOnSell" :key="type.id">New {{type.name }}: {{  Math.round(100*(Math.round((transaction.sellPrice - scope.row.value)*100)/100 * transaction.items[scope.row.id] * transaction.usdhuf * type.percentage / 100))/100 }} Ft</p>
            </div>
            <div v-else>
              <el-tag type="danger">{{ Math.round((transaction.sellPrice - scope.row.value)*100)/100 }}</el-tag>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <span slot="footer" class="dialog-footer">
      <el-button @click="cancelTransaction">Cancel</el-button>
      <el-button type="primary" @click="submitTransaction">Confirm</el-button>
    </span>
  </el-dialog>
</template>

<script>
import repo from '../../repositories/RSURepository'

export default {
  name: 'RSUSell',
  data () {
    return {
      transaction: {
        'income': 0,
        'fee': 0,
        'sellPrice': 0.00,
        'usdhuf': 0.00,
        'items': {}
      }
    }
  },
  computed: {
    isRsuSellVisible () {
      return this.$store.getters['RSUStore/isRsuSellVisible']
    },
    rsus () {
      return this.$store.getters['RSUStore/getRsus']
    },
    taxesOnSell () {
      return this.$store.getters['TaxTypeStore/getTaxTypesOnSell']
    }
  },
  methods: {
    async submitTransaction () {
      console.log(this.transaction)
      const response = await repo.sell(this.transaction).catch(
        error => this.$message.warning(error.message)
      )
      console.log(response)
      this.close()
    },
    cancelTransaction () {
      this.close()
    },
    close () {
      this.$store.commit('RSUStore/setRsuSellVisible', false)
    }
  }
}
</script>

<style scoped>

</style>
