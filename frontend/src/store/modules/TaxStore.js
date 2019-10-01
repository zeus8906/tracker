export const state = {
  taxes: []
}

export const getters = {
  getTaxes: state => state.taxes,
  getTaxesByRsu: state => rsuId => state.taxes.filter(tax => tax.rsu.id === rsuId)
}

export const mutations = {
  setTaxes: (state, newTaxes) => {
    state.taxes = newTaxes
  },
  addTax: (state, newTax) => {
    state.taxes.push(newTax)
  },
  deleteTax (state, taxId) {
    state.taxes = state.taxes.filter(tax => tax.id !== parseInt(taxId))
  },
  updateTax (state, updated) {
    state.taxes = state.taxes.map(tax => { return tax.id === updated.id ? updated : tax })
  }
}
