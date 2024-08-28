import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

import "./Invoice.css";

const Invoice = () => {
  const [invoices, setInvoices] = useState([]);

  useEffect(() => {
    loadInvoices();
  }, []);

  const loadInvoices = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/invoices`);
      setInvoices(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div>
      <div className="invoice-contain">
        <h1>Invoice</h1>
        <Button variant="primary" href="/create_invoice">
          New
        </Button>
      </div>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Invoice Date</th>
            <th>Total Amount</th>
            <th>Paid Amount</th>
            <th>Owe Amount</th>
            <th>Customer Name</th>
            <th>Customer Phone</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {invoices.map((invoice, index) => {
            return (
              <tr key={index}>
                <td>{index + 1}</td>
                <td>
                  {new Date(invoice.invoiceDate)
                    .toISOString()
                    .slice(0, 19)
                    .replace("T", " ")}
                </td>
                <td>{invoice.totalAmount}</td>
                <td>{invoice.paidAmount}</td>
                <td>{invoice.oweAmount}</td>
                <td>{invoice.customer.name}</td>
                <td>{invoice.customer.phone}</td>
                <td>
                  <Button variant="warning">Edit</Button>
                  {"   "}
                  <Button variant="danger">Delete</Button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
};

export default Invoice;
