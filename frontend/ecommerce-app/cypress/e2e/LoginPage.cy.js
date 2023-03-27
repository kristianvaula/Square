describe('e2e test for LoginPage', () => {
    it('Possible to logg in with a user with valid credentials', () => {
        cy.visit('http://localhost:8080/#/login');
        
        cy.get("[data-test='eMail']").eq(0).type("test@mail.com")
        cy.get("[data-test='password']").eq(0).type("Password1!")

        cy.get('[data-test="submitBtn"]').click()

        cy.url().should('eq', 'http://localhost:8080/#/') 
    })

    
  it('Display an error message when logg in is tried with invalid credentials', () => {
    cy.visit('http://localhost:8080/#/login')

    // Enter invalid email and password
    cy.get('[data-test="eMail"]').eq(0).type('invaliduser@example.com')
    cy.get('[data-test="password"]').eq(0).type('invalidpassword')

    // Click submit button
    cy.get('[data-test="submitBtn"]').click()

    // Assert that an error message is displayed
    cy.get('[data-test="errorMessage"]').should('contain', 'Crendtials is invalid')
  })

  it('Display an error message when logg in is tried when all fileds not is displayed', () => {
    cy.visit('http://localhost:8080/#/login')

    // Enter invalid email and password
    cy.get('[data-test="eMail"]').eq(0).type('invaliduser@example.com')

    // Click submit button
    cy.get('[data-test="submitBtn"]').click()

    // Assert that an error message is displayed
    cy.get('[data-test="errorMessage"]').should('contain', 'Please fill in all filds')
  })
})