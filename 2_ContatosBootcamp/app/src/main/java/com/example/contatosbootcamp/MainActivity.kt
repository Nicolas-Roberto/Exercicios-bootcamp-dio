package com.example.contatosbootcamp

import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val REQUEST_CONTACT = 1
    val LINEAR_LAYOUT_VERTICAL = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CONTACT)
        } else {
            setContacts()
        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        if(requestCode == REQUEST_CONTACT) setContacts()
    }

    @SuppressLint("Range")
    private fun setContacts() {
        val contactList: ArrayList<Contact> = ArrayList()

        val profileCursor = contentResolver.query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null)

        if( profileCursor != null ) {
            while(profileCursor.moveToNext()) {
                contactList.add(
                    Contact(
                        profileCursor.getString(profileCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        profileCursor.getString(profileCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    ))
            }
            profileCursor.close()
        }
        val adapter = ContactsAdapter(contactList)
        val contactRecycleView = findViewById<RecyclerView>(R.id.contacts_recycle_view)

        contactRecycleView.layoutManager = LinearLayoutManager(
            this,
            LINEAR_LAYOUT_VERTICAL,
            false)
        contactRecycleView.adapter = adapter
    }
}