<template>
  <el-dialog width="60%" center :visible="isRsuSellVisible" @close="close">
    <el-row :gutter="20">
      <el-form :model="transaction">
        <el-form-item label="Sell Price">
          <el-input-number v-model="transaction.sellPrice" :min="0" :precision="2"></el-input-number>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row :gutter="20">
      <el-table :data="rsus" height="200" stripe border>
        <el-table-column label="Id" prop="id"></el-table-column>
        <el-table-column label="RSU">
          <template slot-scope="scope">
            Settle date: {{ scope.row.settleDate | dateFormatter }} <br>
            #{{ scope.row.count }} X ${{ scope.row.value }} = $ {{ scope.row.count * scope.row.value | currencyFormatter}}
          </template>
        </el-table-column>
        <el-table-column label="To Sell">
          <template slot-scope="scope">
            <el-slider :min="0" :max="scope.row.count" :step="1" show-input v-model="sellCounts[scope.row.id]"> </el-slider>
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
export default {
  name: 'RSUSell',
  data () {
    return {
      transaction: {
        'count': 0,
        'sellPrice': 0.00,
        'usdhuf': 0.00,
        'items': []
      },
      sellCounts: [],
      sellPrice: 0.00
    }
  },
  computed: {
    isRsuSellVisible () {
      return this.$store.getters['RSUStore/isRsuSellVisible']
    },
    rsus () {
      return this.$store.getters['RSUStore/getRsus']
    }
  },
  methods: {
    submitTransaction () {
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
