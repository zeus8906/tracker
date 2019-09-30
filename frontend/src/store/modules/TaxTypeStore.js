export const state = {
  taxTypes: []
}

export const getters = {
  getTaxTypes: state => state.taxTypes
}

export const mutations = {
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
}
