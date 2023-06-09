describe('e2e test for RegisterProfilePage', () => {
  it('Possible to fill in and submit a vadlid registered form', () => {
    cy.visit('http://localhost:8080/#/create-profile')

    cy.get("[data-test='firstName']").eq(0).type("Ola")
    cy.get('[data-test="lastName"]').eq(0).type('Nordmann')
    cy.get('[data-test="eMail"]').eq(0).type('ola@mail.com')
    cy.get('[data-test="county"]').eq(0).select('Viken')
    cy.get('[data-test="city"]').eq(0).type('Oslo')
    cy.get('[data-test="address"]').eq(0).type('Oslogate 5')
    cy.get('[data-test="password"]').eq(0).type('Password1!')

    cy.get('[data-test="registerBtn"]').click()

    cy.url().should('eq', 'http://localhost:8080/#/') 
  })

  it('Displays error message if registering a profile with an existing email', () => {
    cy.visit('http://localhost:8080/#/create-profile')

    cy.get("[data-test='firstName']").eq(0).type("Ola")
    cy.get('[data-test="lastName"]').eq(0).type('Iversen')
    cy.get('[data-test="eMail"]').eq(0).type('ola@mail.com')
    cy.get('[data-test="county"]').eq(0).select('Agder')
    cy.get('[data-test="city"]').eq(0).type('Kristiansand')
    cy.get('[data-test="address"]').eq(0).type('Kristiansandsgate 5')
    cy.get('[data-test="password"]').eq(0).type('Password1!')

    //simulating a request to create a new profile
    cy.request({
      method: 'POST',
      url: 'http://localhost:8081/unauthorized/new-profile',
      body: {
        firstName: 'Ola',
        lastName: 'Iversen',
        eMail: 'ola@mail.com',
        county: 'Agder',
        city: 'Kristiansand',
        address: 'Kristiansandsgate 5',
        password: 'Password1!'
      },
      failOnStatusCode: false 
    }).then((response) => {
      expect(response.status).to.equal(409) // eror 409 - status code for conflict (already exists an eMail)  
    })
  })
})





