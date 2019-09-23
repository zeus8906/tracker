import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    rsus: [],
    taxes: [],
    taxBase: 0,
    taxTypes: []
  },
  getters: {
    getRsus: state => {
      return state.rsus
    },
    taxes: state => {
      return state.taxes
    },
    getTaxBase: state => {
      return state.taxBase
    },
    getTaxTypes: state => {
      return state.taxTypes
    }
  },
  mutations: {
    addRsu (state, newRsu) {
      state.rsus.push(newRsu)
    },
    setRsus (state, rsuList) {
      state.rsus = rsuList
    },
    deleteRsu (state, rsuId) {
      state.rsus = state.rsus.filter(rsu => rsu.id !== parseInt(rsuId))
    },
    updateRsu (state, updated) {
      state.rsus.map(rsu => { return rsu.id === updated.id ? updated : rsu })
    },
    setTaxBase (state, newTaxBase) {
      state.taxBase = newTaxBase
    },
    setTaxTypes (state, taxTypes) {
      state.taxTypes = taxTypes
    },
    addTaxType (state, newTaxType) {
      state.taxTypes.push(newTaxType)
    },
    deleteTaxType (state, taxTypeId) {
      state.taxTypes = state.taxTypes.filter(type => type.id !== parseInt(taxTypeId))
    },
    updateTaxType (state, updated) {
      state.taxTypes.map(type => { return type.id === updated.id ? updated : type })
    }
  },
  actions: {
    loadRsus ({ commit }) {
      axios.get('/api/rsu/get_all').then(response => response.data).then(rsus => { commit('setRsus', rsus) })
    },
    async loadTaxBase ({ commit }) {
      const resp = await axios.get('/api/args/tax_base')
      commit('setTaxBase', resp.data)
    },
    loadTaxTypes ({ commit }) {
      axios.get('/api/tax_type/get_all').then(response => response.data).then(taxTypes => { commit('setTaxTypes', taxTypes) })
    }
  }
})
