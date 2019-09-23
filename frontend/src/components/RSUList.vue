<template>
  <div>
    <el-table height="200" stripe border
              :data="rsus"
              slot="No data">
      <el-table-column prop="id" label="Id" width="40"></el-table-column>
      <el-table-column prop="settleDate" label="Date" width="100" :formatter="dateFormat"></el-table-column>
      <el-table-column prop="count" label="Count"></el-table-column>
      <el-table-column prop="value" label="Value" :formatter="dollarFormat"></el-table-column>
      <el-table-column prop="usdhuf" label="USD to HUF"></el-table-column>
      <el-table-column label="Operations" width="170">
        <template slot-scope="scope">
          <el-button size="mini" @click="editRSU(scope.$index, rsus)">Edit</el-button>
          <el-button size="mini" type="danger" @click="deleteRSU(scope.$index, rsus)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button size="medium" type="primary" @click="isAddFormVisible=true">Add</el-button>
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
          <el-checkbox v-model="newRSU.isTaxPaid"> Tax Paid</el-checkbox>
        </el-form-item>
      </el-form>
      </el-col>
        <el-col :span="14">
          <h4> Earning: {{ newRSU.count * newRSU.value * newRSU.usdhuf }} Ft</h4>
          <h4> Tax Base ( #{{ newRSU.count }} X ${{ newRSU.value }} X {{ newRSU.usdhuf }} X {{ this.taxBase }}% ) = {{ this.exactTaxBase }} Ft</h4>
          <h4> Taxes: </h4>
          <el-table :data="taxTypes">
            <el-table-column prop="name" label="Type"></el-table-column>
            <el-table-column prop="percentage" label="Percentage"></el-table-column>
            <el-table-column prop="percentage" label="Value" :formatter="countTaxType"></el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
          <el-button @click="closeForm">Cancel</el-button>
          <el-button type="primary" @click="submitForm">Confirm</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import moment from 'moment'

export default {
  name: 'RSUList',
  data () {
    return {
      isAddFormVisible: false,
      indexOfUpdate: -1,
      newRSU: {
        'count': 0
      }
    }
  },
  computed: {
    rsus () {
      return this.$store.getters.getRsus
    },
    taxBase () {
      return this.$store.getters.getTaxBase
    },
    taxTypes () {
      return this.$store.getters.getTaxTypes
    },
    exactTaxBase () {
      return this.newRSU.count * this.newRSU.value * this.newRSU.usdhuf * (this.taxBase / 100.00)
    }
  },
  mounted () {
    this.$store.dispatch('loadRsus')
  },
  methods: {
    dollarFormat (row, col, val, index) {
      return '$' + val
    },
    countTaxType (row, col, val, index) {
      return this.exactTaxBase * val / 100.0 + ' Ft'
    },
    dateFormat (row, col, val, index) {
      return moment(String(val)).format('YYYY-MM-DD')
    },
    submitForm () {
      var promise = this.newRSU.id ? axios.put('/api/rsu/update', this.newRSU)
        : axios.post('/api/rsu/add', this.newRSU)
      promise.then(response => {
        if (this.indexOfUpdate >= 0) {
          this.$store.commit('updateRsu', response.data)
        } else {
          this.$store.commit('addRsu', response.data)
        }
        this.closeForm()
      })
    },
    closeForm () {
      this.newRSU = {
        'count': 0
      }
      this.indexOfUpdate = -1
      this.isAddFormVisible = false
    },
    deleteRSU (index, rows) {
      axios.delete('/api/rsu/delete/' + rows[index].id).then(response => { response.data && this.$store.commit('deleteRsu', rows[index].id) })
    },
    editRSU (index, rows) {
      this.newRSU = rows[index]
      this.indexOfUpdate = index
      this.isAddFormVisible = true
    }
  }
}
</script>

<style>
</style>
