<template>
  <div>
    <el-table height="200" stripe border
              :data="taxes"
              slot="No data">
      <el-table-column prop="rsu.id" label="Rsu Id"></el-table-column>
      <el-table-column label="Due Date">
        <template slot-scope="scope">
          {{ scope.row.dueDate | dateFormatter }}
        </template>
      </el-table-column>
      <el-table-column prop="taxType.name" label="Type"></el-table-column>
      <el-table-column label="Value">
        <template slot-scope="scope">
          {{ scope.row.value | currencyFormatter }}
        </template>
      </el-table-column>
      <el-table-column prop="paid" label="Status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.paid" type="success" disable-transitions> Paid</el-tag>
          <el-button v-else @click="payTax(scope.row)">Pay Tax</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import repo from '../repositories/TaxRepository'

export default {
  name: 'TaxList',
  computed: {
    taxes () {
      return this.$store.getters['TaxStore/getTaxes']
    }
  },
  async mounted () {
    try {
      const loaded = await repo.getAll()
      this.$store.commit('TaxStore/setTaxes', loaded)
    } catch (e) {
      this.$message.warning(e.message)
    }
  },
  methods: {
    hufFormat (row, col, val, index) {
      return val + ' Ft'
    },
    async payTax (tax) {
      tax.paid = true
      try {
        const updated = await repo.update(tax)
        this.$store.commit('TaxStore/updateTax', updated)
      } catch (e) {
        this.$message.warning(e.message)
      }
    }
  }
}
</script>

<style>
</style>
