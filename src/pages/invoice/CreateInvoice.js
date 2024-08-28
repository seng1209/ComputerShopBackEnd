import React, { useState, useEffect } from "react";
import axios from "axios";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const CreateInvoice = () => {
  const [disable, setDisable] = useState(false);

  const [customers, setCustomers] = useState([]);

  const [invoiceRequest, setInvoiceRequest] = useState({
    oweAmount: "",
    customerPhone: "",
  });

  const [invoiceResponse, setInvoiceResponse] = useState({
    invoiceDate: "",
    totalAmount: "",
    paidAmount: "",
    oweAmount: "",
    customer: "",
  });

  useEffect(() => {
    loadCustomers();
  }, []);

  const loadCustomers = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/customers`);
      setCustomers(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  // Master Form
  const onMasterInputChange = (e) => {
    setInvoiceRequest({ ...invoiceRequest, [e.target.name]: e.target.value });
  };

  const onSubmitMasterForm = async () => {
    console.log(invoiceRequest);
    try {
      const result = await axios.post(
        `http://localhost:8080/api/v1/invoices`,
        invoiceRequest
      );
      setInvoiceResponse(result.data);
      console.log(result.data);
    } catch (err) {
      console.log(err);
    }
    setDisable(true);
  };
  return (
    <div>
      <Container>
        <Row>
          <Col>
            <Form>
              <Row>
                <Col>
                  <Form.Label>Customer Phone</Form.Label>
                  <Form.Select
                    name="customerPhone"
                    onChange={(e) => onMasterInputChange(e)}
                    aria-label="Default select example"
                  >
                    <option disabled selected>
                      Customer Phone
                    </option>
                    {customers.map((customer, index) => {
                      return (
                        <option key={index} value={customer.phone}>
                          {customer.phone}
                        </option>
                      );
                    })}
                  </Form.Select>
                </Col>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Owe Amount</Form.Label>
                    <Form.Control
                      type="number"
                      name="oweAmount"
                      onChange={(e) => onMasterInputChange(e)}
                      placeholder="owe amount"
                    />
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col md={4}>
                  <Button
                    disabled={disable}
                    variant="primary"
                    onClick={onSubmitMasterForm}
                  >
                    Add New Invoice
                  </Button>
                </Col>
              </Row>
            </Form>
          </Col>
          <Col>
            <Form>
              <Form.Label>Customer Phone</Form.Label>
              <Form.Select aria-label="Default select example">
                <option>Open this select menu</option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
              </Form.Select>
              <Form.Group
                className="mb-3"
                controlId="exampleForm.ControlInput1"
              >
                <Form.Label>Owe Amount</Form.Label>
                <Form.Control type="number" placeholder="owe amount" />
              </Form.Group>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default CreateInvoice;

// CreateInvoice
